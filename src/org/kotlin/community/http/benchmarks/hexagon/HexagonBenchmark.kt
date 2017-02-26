package org.kotlin.community.http.benchmarks.hexagon

import co.there4.hexagon.web.servlet.*
import org.kotlin.community.http.benchmarks.*
import java.net.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<HexagonBenchmark>()
    }
}

open class HexagonBenchmark : HttpBenchmarkBase() {
    private lateinit var server: JettyServletServer
    override fun startServer(port: Int) {
        server = JettyServletServer(InetAddress.getLocalHost(), port)
        server.get("/") { ok("Hello") }
        server.run()
    }

    override fun stopServer() {
        server.stop()
    }
}