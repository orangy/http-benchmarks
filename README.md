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
* Grizzly

#### Excluded
* FluentHttp – too slow
* Hexagon – doesn't start with latest Jetty
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
  * Ratpack
  * com.sun.net.httpserver (?)
* other JVM http clients 
* comments on better benchmarking techniques (please, be specific)

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
Benchmark                                     Mode  Cnt   Score   Error   Units
o.k.c.h.b.undertow.UndertowBenchmark.hello   thrpt    5  67.198 ± 1.248  ops/ms
o.k.c.h.b.netty.NettyBenchmark.hello         thrpt    5  64.535 ± 1.202  ops/ms
o.k.c.h.b.jetty.JettyBenchmark.hello         thrpt    5  60.434 ± 0.798  ops/ms
o.k.c.h.b.vertx.VertxBenchmark.hello         thrpt    5  58.670 ± 2.461  ops/ms
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello     thrpt    5  52.120 ± 1.019  ops/ms
o.k.c.h.b.spark.SparkBenchmark.hello         thrpt    5  47.134 ± 0.876  ops/ms
o.k.c.h.b.ktor.KtorJettyBenchmark.hello      thrpt    5  42.336 ± 0.756  ops/ms
o.k.c.h.b.akka.AkkaHttpBenchmark.hello       thrpt    5  40.740 ± 2.619  ops/ms
o.k.c.h.b.ktor.KtorNettyBenchmark.hello      thrpt    5  36.859 ± 1.227  ops/ms
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello  thrpt    5  19.870 ± 2.650  ops/ms
```

## Thanks
Based on prior work by [rocketraman](https://github.com/rocketraman/kotlin-web-hello-world)