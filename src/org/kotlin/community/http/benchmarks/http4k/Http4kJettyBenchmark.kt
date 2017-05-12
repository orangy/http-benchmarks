package org.kotlin.community.http.benchmarks.http4k

import org.http4k.core.*
import org.http4k.core.Status.Companion.OK
import org.http4k.server.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<Http4kJettyBenchmark>()
    }
}

open class Http4kJettyBenchmark : HttpBenchmarkBase() {
    private lateinit var server: Http4kServer
    override fun startServer(port: Int) {
        server = { _: Request -> Response(OK).body("Hello") }.asServer(Jetty(port)).start()
    }

    override fun stopServer() {
        server.stop()
    }
}

