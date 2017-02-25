package org.kotlin.community.http.benchmarks.spark

import org.kotlin.community.http.benchmarks.*
import spark.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<SparkBenchmark>()
    }
}

open class SparkBenchmark : HttpBenchmarkBase(){
    override fun startServer(port: Int) {
        Spark.port(port)
        Spark.get("/") { req, res -> "Hello" }
        Spark.awaitInitialization()
    }

    override fun stopServer() {
        Spark.stop()
    }
}