package org.kotlin.community.http.benchmarks.ninjaframework

import ninja.standalone.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<NinjaBenchmark>()
    }
}

open class NinjaBenchmark : HttpBenchmarkBase() {
    private lateinit var server: NinjaJetty
    override fun startServer(port: Int) {
        server = NinjaJetty().port(port)
        server.start()
    }

    override fun stopServer() {
        server.shutdown()
    }
}

