# Server Suite
_Order is alphabetical_

#### Measured
* AkkaHttp
* Colossus
* FluentHttp 
* Grizzly
* Jetty
* Ktor (Jetty & Netty)
* NanoHttp
* Netty
* NinjaFramework
* SparkJava
* Undertow
* Vert.x

#### Excluded
* Hexagon – doesn't start with latest Jetty
* Wasabi – doesn't survive the load

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
o.k.c.h.b.undertow.UndertowBenchmark.hello      thrpt    5  69.635 ± 0.279  ops/ms
o.k.c.h.b.netty.NettyBenchmark.hello            thrpt    5  66.087 ± 2.201  ops/ms
o.k.c.h.b.colossus.ColossusBenchmark.hello      thrpt    5  63.633 ± 1.191  ops/ms
o.k.c.h.b.vertx.VertxBenchmark.hello            thrpt    5  61.173 ± 2.105  ops/ms
o.k.c.h.b.jetty.JettyBenchmark.hello            thrpt    5  57.401 ± 1.864  ops/ms
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello        thrpt    5  52.446 ± 3.108  ops/ms
o.k.c.h.b.spark.SparkBenchmark.hello            thrpt    5  48.057 ± 1.223  ops/ms
o.k.c.h.b.ktor.KtorJettyBenchmark.hello         thrpt    5  42.654 ± 0.672  ops/ms
o.k.c.h.b.akka.AkkaHttpBenchmark.hello          thrpt    5  39.562 ± 3.835  ops/ms
o.k.c.h.b.ktor.KtorNettyBenchmark.hello         thrpt    5  35.735 ± 2.110  ops/ms
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello     thrpt    5  20.259 ± 1.828  ops/ms
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello   thrpt    5  12.045 ± 0.674  ops/ms
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello  thrpt    5   1.219 ± 0.040  ops/ms
```

## Memory

Allocation rates per request as reported by JMH GC profiler.
```
o.k.c.h.b.jetty.JettyBenchmark.hello:·gc.alloc.rate.norm                              thrpt    5    17311.183 ±     925.125    B/op
o.k.c.h.b.undertow.UndertowBenchmark.hello:·gc.alloc.rate.norm                        thrpt    5    17539.272 ±    1353.671    B/op
o.k.c.h.b.netty.NettyBenchmark.hello:·gc.alloc.rate.norm                              thrpt    5    18159.731 ±      11.198    B/op
o.k.c.h.b.vertx.VertxBenchmark.hello:·gc.alloc.rate.norm                              thrpt    5    18356.052 ±    5138.742    B/op
o.k.c.h.b.spark.SparkBenchmark.hello:·gc.alloc.rate.norm                              thrpt    5    19344.066 ±      79.825    B/op
o.k.c.h.b.colossus.ColossusBenchmark.hello:·gc.alloc.rate.norm                        thrpt    5    19795.220 ±      30.405    B/op
o.k.c.h.b.ktor.KtorJettyBenchmark.hello:·gc.alloc.rate.norm                           thrpt    5    23078.183 ±   14589.106    B/op
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello:·gc.alloc.rate.norm                       thrpt    5    23194.923 ±   12469.246    B/op
o.k.c.h.b.ktor.KtorNettyBenchmark.hello:·gc.alloc.rate.norm                           thrpt    5    25193.748 ±   18345.454    B/op
o.k.c.h.b.akka.AkkaHttpBenchmark.hello:·gc.alloc.rate.norm                            thrpt    5    29709.269 ±      21.732    B/op
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello:·gc.alloc.rate.norm                          thrpt    5    42525.645 ±   55506.923    B/op
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello:·gc.alloc.rate.norm                     thrpt    5   231172.863 ±  457105.347    B/op
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello:·gc.alloc.rate.norm                    thrpt    5  2791458.154 ± 5972694.417    B/op
```

## Thanks
Based on prior work by [rocketraman](https://github.com/rocketraman/kotlin-web-hello-world)
