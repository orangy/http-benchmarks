package org.kotlin.community.http.benchmarks.ktor

import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.host.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.routing.*
import org.kotlin.community.http.benchmarks.*
import java.util.concurrent.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<KtorJettyBenchmark>()
        run<KtorNettyBenchmark>()
    }
}

abstract class KtorBenchmark(val factory: ApplicationHostFactory<ApplicationHost>) : HttpBenchmarkBase() {
    private lateinit var server: ApplicationHost
    override fun startServer(port: Int) {
        server = embeddedServer(factory, port) {
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
