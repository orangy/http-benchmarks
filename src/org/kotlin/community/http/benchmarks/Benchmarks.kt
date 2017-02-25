package org.kotlin.community.http.benchmarks

import org.kotlin.community.http.benchmarks.akka.*
import org.kotlin.community.http.benchmarks.fluenthttp.*
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
        run<JettyBenchmark>()
        run<KtorJettyBenchmark>()
        run<KtorNettyBenchmark>()
        run<NanoHttpBenchmark>()
        run<NettyBenchmark>()
        run<SparkBenchmark>()
        run<UndertowBenchmark>()
        run<VertxBenchmark>()

        // run<FluentHttpBenchmark>() // Excluded because it is too slow (~1.5 ops/ms)
        // run<WasabiBenchmark>() // Excluded because it doesn't survive the load
    }
}

/*
Benchmark results on my machine
MacBook Pro Late 2013, 2.6 GHz Intel Core i7, 16 GB 1600 MHz DDR3, macOS Sierra 10.12.3

Benchmark                                        Mode  Cnt   Score    Error   Units
o.k.c.h.b.undertow.UndertowBenchmark.hello      thrpt    5  66.323 ±  1.777  ops/ms
o.k.c.h.b.netty.NettyBenchmark.hello            thrpt    5  58.032 ± 13.530  ops/ms
o.k.c.h.b.vertx.VertxBenchmark.hello            thrpt    5  58.107 ±  2.351  ops/ms
o.k.c.h.b.jetty.JettyBenchmark.hello            thrpt    5  52.718 ± 12.607  ops/ms
o.k.c.h.b.spark.SparkBenchmark.hello            thrpt    5  46.610 ±  0.810  ops/ms
o.k.c.h.b.akka.AkkaHttpBenchmark.hello          thrpt    5  39.428 ±  3.524  ops/ms
o.k.c.h.b.ktor.KtorJettyBenchmark.hello         thrpt    5  37.258 ±  9.114  ops/ms
o.k.c.h.b.ktor.KtorNettyBenchmark.hello         thrpt    5  36.353 ±  1.352  ops/ms
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello     thrpt    5  19.837 ±  2.244  ops/ms
*/
