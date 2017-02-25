package org.kotlin.community.http.benchmarks.jetty

import org.eclipse.jetty.server.*
import org.eclipse.jetty.server.handler.*
import org.kotlin.community.http.benchmarks.*
import javax.servlet.http.*


fun main(args: Array<String>) {
    benchmark(args) {
        run<JettyBenchmark>()
    }
}

open class JettyBenchmark : HttpBenchmarkBase() {
    private lateinit var server: Server
    override fun startServer(port: Int) {
        server = Server(port)
        server.handler = object : AbstractHandler() {
            override fun handle(target: String, baseRequest: Request, request: HttpServletRequest, response: HttpServletResponse) {
                response.contentType = "text/plain"
                response.status = HttpServletResponse.SC_OK
                response.writer.println("Hello")
                baseRequest.isHandled = true
            }
        }
        server.start()
    }

    override fun stopServer() {
        server.stop()
    }
}