package org.kotlin.community.http.benchmarks.jlhttp

import net.freeutils.httpserver.HTTPServer
import net.freeutils.httpserver.HTTPServer.*
import org.kotlin.community.http.benchmarks.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    benchmark(args) {
        run<JLHTTPBenchmark>()
    }
}


open class JLHTTPBenchmark : HttpBenchmarkBase() {
    private lateinit var server : HTTPServer
    private lateinit var executor: ExecutorService
    override fun startServer(port: Int) {
        executor = Executors.newCachedThreadPool()
        server = HTTPServer(port)
        server.setSocketTimeout(1000)
        server.setExecutor(executor)
        val host = server.getVirtualHost(null)
        host.addContext("/", ContextHandler { req, resp ->
            resp.send(200, "Hello")
            0
        })
        server.start()
    }

    override fun stopServer() {
        server.stop()
        executor.shutdown()
    }
}
