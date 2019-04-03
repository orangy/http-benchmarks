# Server Suite
_Order is alphabetical_

#### Measured
* AkkaHttp
* Colossus
* FluentHttp 
* Grizzly
* Hexagon 
* Http4k (Jetty & Netty) 
* Jetty
* JLHTTP
* Ktor (Jetty & Netty)
* NanoHttp
* Netty
* NinjaFramework
* Rapidoid
* SparkJava
* Undertow
* Vert.x

# Building
* Run maven to fetch dependencies, compile code and generate JMH sources.
```
mvn clean package
```

# Running benchmarks

From IntelliJ (or your favorite IDE with Kotlin support):
* Open Benchmarks.kt and run `main` to benchmark the whole suite
* or, open xxxBenchmark.kt and run benchmark for specific server

# Profiling & Daemon
If you are working on adding your favorite server to benchmark to send me a PR, you might want to
run it as daemon to see if response is actually what it should be. Or you might want to profile your framework
if you think it is underperforming. 

Add the following to the command line arguments to switch modes:
* `profile` – to run multithreaded load simulation without JMH (see BenchmarkRunner.kt for iterations) 
* `daemon` – to run just setup for a benchmark and wait for a key, so you can point your browser to `localhost:5678`
  and see what's going on

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

## Performance

```
Benchmark                                        Mode  Cnt   Score   Error   Units
o.k.c.h.b.undertow.UndertowBenchmark.hello      thrpt    5  66.945 ± 1.675  ops/ms
o.k.c.h.b.netty.NettyBenchmark.hello            thrpt    5  62.882 ± 2.125  ops/ms
o.k.c.h.b.colossus.ColossusBenchmark.hello      thrpt    5  61.768 ± 1.118  ops/ms
o.k.c.h.b.jetty.JettyBenchmark.hello            thrpt    5  56.399 ± 0.733  ops/ms
o.k.c.h.b.ktor.KtorJettyBenchmark.hello         thrpt    5  52.983 ± 2.498  ops/ms
o.k.c.h.b.ktor.KtorNettyBenchmark.hello         thrpt    5  52.081 ± 1.107  ops/ms
o.k.c.h.b.vertx.VertxBenchmark.hello            thrpt    5  52.069 ± 1.872  ops/ms
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello        thrpt    5  51.886 ± 0.811  ops/ms
o.k.c.h.b.spark.SparkBenchmark.hello            thrpt    5  46.153 ± 1.235  ops/ms
o.k.c.h.b.hexagon.HexagonBenchmark.hello        thrpt    5  45.912 ± 1.919  ops/ms
o.k.c.h.b.rapidoid.RapidoidBenchmark.hello      thrpt    5  43.394 ± 1.343  ops/ms
o.k.c.h.b.akka.AkkaHttpBenchmark.hello          thrpt    5  38.987 ± 3.348  ops/ms
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello     thrpt    5  19.864 ± 1.377  ops/ms
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello   thrpt    5  12.046 ± 0.266  ops/ms
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello  thrpt    5   1.119 ± 0.027  ops/ms
```

## Memory

Allocation rates per request as reported by JMH GC profiler.
```
Benchmark                                                                         Mode  Cnt        Score        Error   Units
o.k.c.h.b.jetty.JettyBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    17702.404 ±    104.701    B/op
o.k.c.h.b.undertow.UndertowBenchmark.hello:·gc.alloc.rate.norm                   thrpt   20    17872.781 ±    313.390    B/op
o.k.c.h.b.netty.NettyBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    18195.157 ±     16.175    B/op
o.k.c.h.b.spark.SparkBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    19538.354 ±    425.519    B/op
o.k.c.h.b.colossus.ColossusBenchmark.hello:·gc.alloc.rate.norm                   thrpt   20    19612.308 ±    764.811    B/op
o.k.c.h.b.hexagon.HexagonBenchmark.hello:·gc.alloc.rate.norm                     thrpt   20    20225.478 ±    637.005    B/op
o.k.c.h.b.vertx.VertxBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    20248.098 ±    870.044    B/op
o.k.c.h.b.rapidoid.RapidoidBenchmark.hello:·gc.alloc.rate.norm                   thrpt   20    21875.677 ±    517.967    B/op
o.k.c.h.b.ktor.KtorJettyBenchmark.hello:·gc.alloc.rate.norm                      thrpt   20    24324.972 ±   1554.888    B/op
o.k.c.h.b.ktor.KtorNettyBenchmark.hello:·gc.alloc.rate.norm                      thrpt   20    25899.332 ±   1943.084    B/op
o.k.c.h.b.akka.AkkaHttpBenchmark.hello:·gc.alloc.rate.norm                       thrpt   20    29382.148 ±   2467.917    B/op
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello:·gc.alloc.rate.norm                  thrpt   20    32291.225 ±   3237.100    B/op
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello:·gc.alloc.rate.norm                     thrpt   20    47513.623 ±   6259.800    B/op
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello:·gc.alloc.rate.norm                thrpt   20   272540.830 ±  51894.658    B/op
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello:·gc.alloc.rate.norm               thrpt   20  3366710.592 ± 684588.788    B/op
```

## Thanks
Based on prior work by [rocketraman](https://github.com/rocketraman/kotlin-web-hello-world)
