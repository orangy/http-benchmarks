package org.kotlin.community.http.benchmarks.ktor

import org.jetbrains.ktor.jetty.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<KtorJettyBenchmark>()
    }
}

open class KtorJettyBenchmark : KtorBenchmark(Jetty)

