package org.kotlin.community.http.benchmarks.undertow

import io.undertow.*
import io.undertow.util.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<UndertowBenchmark>()
    }
}

open class UndertowBenchmark : HttpBenchmarkBase() {
    private lateinit var server: Undertow

    override fun startServer(port: Int) {
        server = Undertow.builder()
                .addHttpListener(port, "localhost")
                .setHandler(Handlers.path()
                        .addExactPath("/") {
                            it.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
                            it.responseSender.send("Hello")
                        }
                       // .addPrefixPath("/", Handlers.resource(ClassPathResourceManager(Thread.currentThread().contextClassLoader, "public")))
                ).build()
        server.start()
    }

    override fun stopServer() {
        server.stop()
    }

}
