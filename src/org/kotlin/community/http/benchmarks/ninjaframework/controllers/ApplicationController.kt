package org.kotlin.community.http.benchmarks.ninjaframework.controllers

import ninja.*
import javax.inject.*

@Singleton
open class ApplicationController {
    fun index(): Result {
        return Results.text().renderRaw("Hello")
    }
}