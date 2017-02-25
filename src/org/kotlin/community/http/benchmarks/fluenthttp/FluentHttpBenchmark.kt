package org.kotlin.community.http.benchmarks.fluenthttp

import net.codestory.http.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<FluentHttpBenchmark>()
    }
}

open class FluentHttpBenchmark : HttpBenchmarkBase() {
    private lateinit var server : WebServer
    override fun startServer(port: Int) {
        server = WebServer().configure { routes ->
            routes.get("/", "Hello")
            //routes.get("/image.png", ClassPaths.getResource("public/image.png"))
        }.start(port)
    }

    override fun stopServer() {
        server.stop()
    }
}