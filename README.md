# Server Suite
_Order is alphabetical_

#### Measured
* AkkaHttp
* Jetty
* Ktor (Jetty & Netty)
* NanoHttp
* Netty
* SparkJava
* Undertow
* Vert.x

#### Excluded
* FluentHttp – too slow
* Wasabi – doesn't survive the load

# Using

* Run maven to fetch dependencies, compile code and generate JMH sources.
```
mvn clean package
```

* Import project into IntelliJ IDEA (or your favorite IDE with Kotlin support)
* Open Benchmarks.kt and run `main` to benchmark the whole suite
* or, open xxxBenchmark.kt and run benchmark for specific server

# Contributing

I'm not very interested in various hacks to speed up your favorite server.
Servers are setup with minimum configuration on purpose.
 
I'm interested in 
* other JVM http servers 
* other JVM http clients 
* comments on better benchmarking techniques (please, be specific)
* 

# Results
JMH is run with 32 threads sending requests against server using OkHttp. 
There are 5 warm-up and 5 measurement iterations.  Warm-up and measurement 
iterations are running for 10 seconds each so that server can have a chance 
to tune itself.  

Benchmark results are from my machine, your mileage may vary.
> MacBook Pro Late 2013,
> 2.6 GHz Intel Core i7,
> 16 GB 1600 MHz DDR3,
> macOS Sierra 10.12.3

```
Benchmark                              Mode  Cnt   Score    Error   Units
undertow.UndertowBenchmark.hello      thrpt    5  66.323 ±  1.777  ops/ms
netty.NettyBenchmark.hello            thrpt    5  58.032 ± 13.530  ops/ms
vertx.VertxBenchmark.hello            thrpt    5  58.107 ±  2.351  ops/ms
jetty.JettyBenchmark.hello            thrpt    5  52.718 ± 12.607  ops/ms
spark.SparkBenchmark.hello            thrpt    5  46.610 ±  0.810  ops/ms
akka.AkkaHttpBenchmark.hello          thrpt    5  39.428 ±  3.524  ops/ms
ktor.KtorJettyBenchmark.hello         thrpt    5  37.258 ±  9.114  ops/ms
ktor.KtorNettyBenchmark.hello         thrpt    5  36.353 ±  1.352  ops/ms
nanohttpd.NanoHttpBenchmark.hello     thrpt    5  19.837 ±  2.244  ops/ms
```

# Thanks
Based on prior work by [rocketraman](https://github.com/rocketraman/kotlin-web-hello-world)