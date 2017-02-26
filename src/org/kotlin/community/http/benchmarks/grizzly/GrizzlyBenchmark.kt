package org.kotlin.community.http.benchmarks.grizzly

import org.glassfish.grizzly.http.server.*
import org.glassfish.grizzly.http.util.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<GrizzlyBenchmark>()
    }
}

open class GrizzlyBenchmark : HttpBenchmarkBase() {
    private lateinit var httpServer: HttpServer

    override fun startServer(port: Int) {
        httpServer = HttpServer()
        val networkListener = NetworkListener("http-listener", "0.0.0.0", port)
        httpServer.addListener(networkListener)
        httpServer.serverConfiguration.addHttpHandler(PlainTextHttpHandler(), "/")
        httpServer.start()
    }

    override fun stopServer() {
        httpServer.shutdown()
    }
}

class PlainTextHttpHandler : HttpHandler() {
    override fun service(request: Request, response: Response) {
        response.setContentType(CONTENT_TYPE)
        response.setHeader(Header.Server, SERVER_VERSION)
        response.writer.write("Hello")
    }

    companion object {
        private val CONTENT_TYPE = ContentType.newContentType("text/plain", "utf-8").prepare()
        val SERVER_VERSION = HeaderValue.newHeaderValue("GRZLY").prepare()
    }
}
