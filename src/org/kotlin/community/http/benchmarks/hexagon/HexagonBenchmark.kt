package org.kotlin.community.http.benchmarks.hexagon

import co.there4.hexagon.web.servlet.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<HexagonBenchmark>()
    }
}

open class HexagonBenchmark : HttpBenchmarkBase() {
    private lateinit var server: JettyServletServer
    override fun startServer(port: Int) {
        server = JettyServletServer(bindPort = port).apply {
            get("/") { ok("Hello") }
            run()
        }
    }

    override fun stopServer() {
        server.stop()
    }
}