package org.kotlin.community.http.benchmarks.ktor

import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<KtorJettyBenchmark>()
        run<KtorNettyBenchmark>()
    }
}
