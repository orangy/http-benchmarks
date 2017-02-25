package org.kotlin.community.http.benchmarks

import okhttp3.*
import org.apache.http.client.methods.*
import org.apache.http.impl.client.*
import java.io.*
import java.net.*

interface BenchmarkHttpClient {
    fun setup()
    fun shutdown()
    fun load(url: String): InputStream
}

class UrlBenchmarkClient : BenchmarkHttpClient {
    override fun setup() {}
    override fun shutdown() {}
    override fun load(url: String) = URL(url).openConnection().inputStream
}

class ApacheBenchmarkClient : BenchmarkHttpClient {
    var httpClient: CloseableHttpClient? = null
    override fun setup() {
        val builder = HttpClientBuilder.create()
        httpClient = builder.build()
    }

    override fun shutdown() {
        httpClient!!.close()
        httpClient = null
    }

    override fun load(url: String): InputStream {
        val httpGet = HttpGet(url)
        val response = httpClient!!.execute(httpGet)
        return response.entity.content
    }
}

class OkBenchmarkClient : BenchmarkHttpClient {
    var httpClient: OkHttpClient? = null
    override fun setup() {
        httpClient = OkHttpClient()
    }

    override fun shutdown() {
        httpClient = null
    }

    override fun load(url: String): InputStream {
        val request = Request.Builder().url(url).build()
        val response = httpClient!!.newCall(request).execute()
        return response.body().byteStream()
    }
}
