# Server Suite
_Order is alphabetical_

#### Measured
* AkkaHttp
* Colossus
* FluentHttp 
* Grizzly
* Hexagon 
* Jetty
* Ktor (Jetty & Netty)
* NanoHttp
* Netty
* NinjaFramework
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
o.k.c.h.b.undertow.UndertowBenchmark.hello      thrpt    5  70.388 ± 2.347  ops/ms
o.k.c.h.b.netty.NettyBenchmark.hello            thrpt    5  65.760 ± 1.071  ops/ms
o.k.c.h.b.colossus.ColossusBenchmark.hello      thrpt    5  63.154 ± 1.314  ops/ms
o.k.c.h.b.vertx.VertxBenchmark.hello            thrpt    5  61.173 ± 2.105  ops/ms
o.k.c.h.b.jetty.JettyBenchmark.hello            thrpt    5  55.182 ± 2.104  ops/ms
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello        thrpt    5  52.745 ± 0.975  ops/ms
o.k.c.h.b.ktor.KtorNettyBenchmark.hello         thrpt    5  52.408 ± 2.866  ops/ms
o.k.c.h.b.spark.SparkBenchmark.hello            thrpt    5  48.425 ± 1.130  ops/ms
o.k.c.h.b.hexagon.HexagonBenchmark.hello        thrpt    5  45.757 ± 0.595  ops/ms
o.k.c.h.b.ktor.KtorJettyBenchmark.hello         thrpt    5  43.475 ± 0.630  ops/ms
o.k.c.h.b.akka.AkkaHttpBenchmark.hello          thrpt    5  39.978 ± 2.994  ops/ms
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello     thrpt    5  20.911 ± 3.312  ops/ms
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello   thrpt    5  11.885 ± 0.613  ops/ms
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello  thrpt    5   1.223 ± 0.044  ops/ms
```

## Memory

Allocation rates per request as reported by JMH GC profiler.
```
Benchmark                                                                         Mode  Cnt        Score         Error   Units
o.k.c.h.b.jetty.JettyBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    17356.741 ±     104.412    B/op
o.k.c.h.b.undertow.UndertowBenchmark.hello:·gc.alloc.rate.norm                   thrpt   20    18016.542 ±     237.042    B/op
o.k.c.h.b.netty.NettyBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    18668.902 ±      21.283    B/op
o.k.c.h.b.vertx.VertxBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    18356.052 ±    5138.742    B/op
o.k.c.h.b.spark.SparkBenchmark.hello:·gc.alloc.rate.norm                         thrpt   20    19610.449 ±     442.252    B/op
o.k.c.h.b.colossus.ColossusBenchmark.hello:·gc.alloc.rate.norm                   thrpt   20    20082.757 ±     771.077    B/op
o.k.c.h.b.hexagon.HexagonBenchmark.hello:·gc.alloc.rate.norm                     thrpt   20    20233.539 ±     627.870    B/op
o.k.c.h.b.ktor.KtorJettyBenchmark.hello:·gc.alloc.rate.norm                      thrpt   20    24854.034 ±    1666.493    B/op
o.k.c.h.b.ktor.KtorNettyBenchmark.hello:·gc.alloc.rate.norm                      thrpt   20    26526.571 ±    2014.653    B/op
o.k.c.h.b.akka.AkkaHttpBenchmark.hello:·gc.alloc.rate.norm                       thrpt   20    29491.101 ±    2488.843    B/op
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello:·gc.alloc.rate.norm                  thrpt   20    30970.826 ±    4143.206    B/op
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello:·gc.alloc.rate.norm                     thrpt   20    47768.914 ±    6258.249    B/op
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello:·gc.alloc.rate.norm                thrpt   20   272792.107 ±   51955.717    B/op
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello:·gc.alloc.rate.norm               thrpt   20  3310991.108 ±  673171.222    B/op
```

## Thanks
Based on prior work by [rocketraman](https://github.com/rocketraman/kotlin-web-hello-world)
