package org.kotlin.community.http.benchmarks.nanohttpd

import fi.iki.elonen.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<NanoHttpBenchmark>()
    }
}


open class NanoHttpBenchmark : HttpBenchmarkBase() {
    private lateinit var server : NanoHTTPD
    override fun startServer(port: Int) {
        server = object : NanoHTTPD(port) {
            override fun serve(session: IHTTPSession): Response = when (session.uri) {
                "/" -> newFixedLengthResponse("Hello")
/*
                "/image.png" -> {
                    val resource = Thread.currentThread().contextClassLoader.getResource("image.png")
                    newChunkedResponse(Response.Status.OK, "image/png", resource.openStream())
                }
*/
                else -> newFixedLengthResponse(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "Not found")
            }
        }
        server.start()
    }

    override fun stopServer() {
        server.stop()
    }
}