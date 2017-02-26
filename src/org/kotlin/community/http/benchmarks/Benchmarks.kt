package org.kotlin.community.http.benchmarks

import org.kotlin.community.http.benchmarks.akka.*
import org.kotlin.community.http.benchmarks.fluenthttp.*
import org.kotlin.community.http.benchmarks.grizzly.*
import org.kotlin.community.http.benchmarks.hexagon.*
import org.kotlin.community.http.benchmarks.jetty.*
import org.kotlin.community.http.benchmarks.ktor.*
import org.kotlin.community.http.benchmarks.nanohttpd.*
import org.kotlin.community.http.benchmarks.netty.*
import org.kotlin.community.http.benchmarks.spark.*
import org.kotlin.community.http.benchmarks.undertow.*
import org.kotlin.community.http.benchmarks.vertx.*
import org.kotlin.community.http.benchmarks.wasabi.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<AkkaHttpBenchmark>()
        run<GrizzlyBenchmark>()
        run<JettyBenchmark>()
        run<KtorJettyBenchmark>()
        run<KtorNettyBenchmark>()
        run<NanoHttpBenchmark>()
        run<NettyBenchmark>()
        run<SparkBenchmark>()
        run<UndertowBenchmark>()
        run<VertxBenchmark>()

        // run<HexagonBenchmark>() // Excluded because of incompatibility(?) with latest Jetty
        // run<FluentHttpBenchmark>() // Excluded because it is too slow (~1.5 ops/ms)
        // run<WasabiBenchmark>() // Excluded because it doesn't survive the load
    }
}

