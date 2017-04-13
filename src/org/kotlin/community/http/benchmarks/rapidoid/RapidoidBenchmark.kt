package org.kotlin.community.http.benchmarks.rapidoid

import org.kotlin.community.http.benchmarks.*
import org.rapidoid.setup.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<RapidoidBenchmark>()
    }
}

open class RapidoidBenchmark : HttpBenchmarkBase() {
    override fun startServer(port: Int) {
        App.bootstrap(arrayOf("on.port=$port"))
        // It's cheating with TechEmpower benchmarks like this:
        // On.get("/hello").managed(false).contentType(MediaType.TEXT_PLAIN).serve("Hello")
        On.get("/hello").plain { -> "Hello" }
    }

    override fun stopServer() {
        App.shutdown()
    }
}
