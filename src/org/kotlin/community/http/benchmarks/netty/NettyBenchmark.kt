package org.kotlin.community.http.benchmarks.netty

import io.netty.bootstrap.*
import io.netty.buffer.*
import io.netty.channel.*
import io.netty.channel.nio.*
import io.netty.channel.socket.nio.*
import io.netty.handler.codec.http.*
import io.netty.util.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<NettyBenchmark>()
    }
}

open class NettyBenchmark : HttpBenchmarkBase() {
    val bossLoopGroup = NioEventLoopGroup()
    val workerLoopGroup = NioEventLoopGroup()

    override fun startServer(port: Int) {
        val bootstrap = ServerBootstrap()
                .option(ChannelOption.SO_BACKLOG, 1024)
                .group(bossLoopGroup, workerLoopGroup)
                .channel(NioServerSocketChannel::class.java)
                //.handler(LoggingHandler(LogLevel.INFO))
                .childHandler(HttpServerInitializer())

        bootstrap.bind(port)
    }

    override fun stopServer() {
        bossLoopGroup.shutdownGracefully()
        workerLoopGroup.shutdownGracefully()
    }
}

class HttpServerInitializer : ChannelInitializer<Channel>() {
    override fun initChannel(ch: Channel) {
        val pipeline = ch.pipeline()
        pipeline.addLast(HttpServerCodec())
        pipeline.addLast(HttpServerHandler())
    }
}

class HttpServerHandler : SimpleChannelInboundHandler<HttpObject>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: HttpObject) {
        if (msg is HttpRequest) {
            val content = Unpooled.copiedBuffer("Hello", CharsetUtil.UTF_8)
            val response = DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content)
            val keepAlive = HttpUtil.isKeepAlive(msg);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain")
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes())
            if (!keepAlive) {
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }
}
