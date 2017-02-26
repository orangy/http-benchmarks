package org.kotlin.community.http.benchmarks.ninjaframework.conf

import ninja.*
import ninja.application.*
import org.kotlin.community.http.benchmarks.ninjaframework.controllers.*

open class Routes : ApplicationRoutes {
    override fun init(router: Router) {
        router.GET().route("/").with(ApplicationController::class.java, "index");
    }
}