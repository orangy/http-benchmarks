package org.kotlin.community.http.benchmarks.wasabi

import org.kotlin.community.http.benchmarks.*
import org.wasabifx.wasabi.app.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<WasabiBenchmark>()
    }
}

open class WasabiBenchmark : HttpBenchmarkBase() {
    private lateinit var server : AppServer
    override fun startServer(port: Int) {
        server = AppServer(AppConfiguration(port = port, enableLogging = false))

        server.get("/", {
            response.contentType = "text/plain"
            response.send("Hello")
        })

/*
        server.get("/image.png", {
            val resource = Thread.currentThread().contextClassLoader.getResource("public/image.png")
            response.sendFile(resource.path, "image/png")
        })
*/

        server.start(false)
    }

    override fun stopServer() {
        server.stop()
    }
}