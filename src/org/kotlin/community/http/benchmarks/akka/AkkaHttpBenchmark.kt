package org.kotlin.community.http.benchmarks.akka

import akka.actor.*
import akka.http.javadsl.*
import akka.http.javadsl.model.*
import akka.http.javadsl.server.*
import akka.stream.*
import org.kotlin.community.http.benchmarks.*

fun main(args: Array<String>) {
    benchmark(args) {
        run<AkkaHttpBenchmark>()
    }
}

open class AkkaHttpBenchmark : HttpBenchmarkBase() {
    private val system: ActorSystem = ActorSystem.create()

    override fun startServer(port: Int) {
        val materializer = ActorMaterializer.create(system)

        val routeFlow = Akka.createRoute().flow(system, materializer)
        val http = Http.get(system)
        http.bindAndHandle(routeFlow, ConnectHttp.toHost("localhost", port), materializer)
    }

    override fun stopServer() {
        system.shutdown()
    }
}

object Akka {
    fun createRoute(): Route {
        return Directives.get {
            Directives.route(
                    // matches the empty path
                    Directives.pathSingleSlash {
                        // return a constant string with a certain content type
                        Directives.complete(HttpEntities.create(ContentTypes.TEXT_PLAIN_UTF8, "Hello"))
                    }
/*
                    , Directives.path("image.png") {
                        Directives.getFromResource("public/image.png")
                    }
*/
            )
        }
    }
}