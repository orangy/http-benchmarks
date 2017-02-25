package org.kotlin.community.http.benchmarks.ktor

import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.jetty.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.routing.*
import org.kotlin.community.http.benchmarks.*
import java.util.concurrent.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<KtorJettyBenchmark>()
    }
}

open class KtorJettyBenchmark : HttpBenchmarkBase() {
    private lateinit var server: JettyApplicationHost
    override fun startServer(port: Int) {
        server = embeddedJettyServer(port) {
            install(Routing) {
                get("/") {
                    call.respondText("Hello")
                }
            }
        }
        server.start()
    }

    override fun stopServer() {
        server.stop(500, 5000, TimeUnit.MILLISECONDS)
    }
}

