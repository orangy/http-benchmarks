package org.kotlin.community.http.benchmarks

import org.kotlin.community.http.benchmarks.akka.AkkaHttpBenchmark
import org.kotlin.community.http.benchmarks.colossus.ColossusBenchmark
import org.kotlin.community.http.benchmarks.fluenthttp.FluentHttpBenchmark
import org.kotlin.community.http.benchmarks.grizzly.GrizzlyBenchmark
import org.kotlin.community.http.benchmarks.hexagon.HexagonBenchmark
import org.kotlin.community.http.benchmarks.http4k.Http4kJettyBenchmark
import org.kotlin.community.http.benchmarks.http4k.Http4kNettyBenchmark
import org.kotlin.community.http.benchmarks.jetty.JettyBenchmark
import org.kotlin.community.http.benchmarks.ktor.KtorJettyBenchmark
import org.kotlin.community.http.benchmarks.ktor.KtorNettyBenchmark
import org.kotlin.community.http.benchmarks.nanohttpd.NanoHttpBenchmark
import org.kotlin.community.http.benchmarks.netty.NettyBenchmark
import org.kotlin.community.http.benchmarks.ninjaframework.NinjaBenchmark
import org.kotlin.community.http.benchmarks.rapidoid.RapidoidBenchmark
import org.kotlin.community.http.benchmarks.spark.SparkBenchmark
import org.kotlin.community.http.benchmarks.undertow.UndertowBenchmark
import org.kotlin.community.http.benchmarks.vertx.VertxBenchmark

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
    run<RapidoidBenchmark>()
}

