package org.kotlin.community.http.benchmarks.http4k

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Http4kServer
import org.http4k.server.Netty
import org.http4k.server.asServer
import org.kotlin.community.http.benchmarks.HttpBenchmarkBase
import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
    benchmark(args) {
        run<Http4kNettyBenchmark>()
    }
}

open class Http4kNettyBenchmark : HttpBenchmarkBase() {
    private lateinit var server: Http4kServer
    override fun startServer(port: Int) {
        server = { _: Request -> Response(OK).body("Hello") }.asServer(Netty(port)).start()
    }

    override fun stopServer() {
        server.stop()
    }
}

