package org.kotlin.community.http.benchmarks

import org.kotlin.community.http.benchmarks.akka.*
import org.kotlin.community.http.benchmarks.colossus.*
import org.kotlin.community.http.benchmarks.fluenthttp.*
import org.kotlin.community.http.benchmarks.grizzly.*
import org.kotlin.community.http.benchmarks.hexagon.*
import org.kotlin.community.http.benchmarks.jetty.*
import org.kotlin.community.http.benchmarks.http4k.*
import org.kotlin.community.http.benchmarks.ktor.*
import org.kotlin.community.http.benchmarks.nanohttpd.*
import org.kotlin.community.http.benchmarks.netty.*
import org.kotlin.community.http.benchmarks.ninjaframework.*
import org.kotlin.community.http.benchmarks.rapidoid.*
import org.kotlin.community.http.benchmarks.spark.*
import org.kotlin.community.http.benchmarks.undertow.*
import org.kotlin.community.http.benchmarks.vertxweb.*

fun main(args: Array<String>) {
    benchmark(args) {
        iterations = 5
        iterationTime = 10_000
        setup()
    }

    benchmark(args) {
        profile("gc")
        iterations = 20
        iterationTime = 500
        setup()
    }
}

private fun BenchmarkSettings.setup() {
    run<AkkaHttpBenchmark>()
    run<ColossusBenchmark>()
    run<FluentHttpBenchmark>()
    run<GrizzlyBenchmark>()
    run<JettyBenchmark>()
    run<HexagonBenchmark>()
    run<Http4kJettyBenchmark>()
    run<Http4kNettyBenchmark>()
    run<KtorJettyBenchmark>()
    run<KtorNettyBenchmark>()
    run<NanoHttpBenchmark>()
    run<NettyBenchmark>()
    run<NinjaBenchmark>()
    run<SparkBenchmark>()
    run<UndertowBenchmark>()
    run<VertxBenchmark>()
    run<VertxWebBenchmark>()
    run<RapidoidBenchmark>()
}

