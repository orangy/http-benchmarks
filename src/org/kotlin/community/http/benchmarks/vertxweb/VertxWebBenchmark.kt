package org.kotlin.community.http.benchmarks.vertxweb

import io.vertx.core.*
import io.vertx.ext.web.*
import org.kotlin.community.http.benchmarks.*
import java.util.concurrent.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<VertxWebBenchmark>()
    }
}

open class VertxWebBenchmark : HttpBenchmarkBase() {
    val vertx = Vertx.vertx()
    override fun startServer(port: Int) {
        val router = Router.router(vertx).apply {
            get("/").handler { request ->
                request.response().putHeader("content-type", "text/plain").end("Hello")
            }
            //get().handler(StaticHandler.create("public"))
        }

        val f = CompletableFuture<Unit>()
        vertx.createHttpServer().requestHandler {
            router.accept(it)
        }.listen(port) {
            f.complete(Unit)
        }
        f.join()
    }

    override fun stopServer() {
        vertx.close()
    }
}