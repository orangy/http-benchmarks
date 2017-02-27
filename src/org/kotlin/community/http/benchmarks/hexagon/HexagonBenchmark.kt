package org.kotlin.community.http.benchmarks.hexagon

import co.there4.hexagon.web.*
import co.there4.hexagon.web.servlet.*
import org.kotlin.community.http.benchmarks.*
import java.net.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<HexagonBenchmark>()
    }
}

open class HexagonBenchmark : HttpBenchmarkBase() {

    override fun startServer(port: Int) {
        server = JettyServletServer(InetAddress.getLocalHost(), port)
        get("/") { ok("Hello") }
        run()
    }

    override fun stopServer() {
        stop()
    }
}
