package org.kotlin.community.http.benchmarks.colossus

import akka.actor.*
import colossus.*
import colossus.core.*
import colossus.protocols.http.*
import colossus.protocols.http.`package`
import colossus.service.*
import org.kotlin.community.http.benchmarks.*
import scala.runtime.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<ColossusBenchmark>()
    }
}

open class ColossusBenchmark : HttpBenchmarkBase() {
    val actorSystem: ActorSystem = ActorSystem.create()
    val io: IOSystem = IOSystem.apply(actorSystem)
    lateinit var server: ServerRef

    override fun startServer(port: Int) {
        val function = object : AbstractFunction1<WorkerRef, Initializer>() {
            override fun apply(worker: WorkerRef) = object : Initializer(worker) {
                override fun onConnect() = object : AbstractFunction1<ServerContext, ServerConnectionHandler>() {
                    override fun apply(context: ServerContext) = object : `package`.HttpService(context) {
                        override fun handle() = object : AbstractPartialFunction<Any, Callback<Any>>() {
                            override fun isDefinedAt(request: Any?): Boolean = true
                            override fun apply(request: Any): Callback<Any> {
                                request as HttpRequest
                                val encoder = `HttpBody$`.`MODULE$`.StringEncoder()
                                return `Callback$`.`MODULE$`.successful(request.ok("Hello", request.`ok$default$2`<String>(), encoder))
                            }
                        }
                    }
                }
            }
        }
        server = `Server$`.`MODULE$`.start("benchmark", port, function, io)
    }

    override fun stopServer() {
        actorSystem.shutdown()
    }
}