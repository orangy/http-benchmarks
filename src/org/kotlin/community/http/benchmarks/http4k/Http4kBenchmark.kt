package org.kotlin.community.http.benchmarks.http4k

import org.kotlin.community.http.benchmarks.benchmark

fun main(args: Array<String>) {
    benchmark(args) {
        run<Http4kJettyBenchmark>()
        run<Http4kNettyBenchmark>()
    }
}
