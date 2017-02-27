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

#### Excluded
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

```
Benchmark                                     Mode  Cnt   Score   Error   Units
o.k.c.h.b.undertow.UndertowBenchmark.hello      thrpt    5  70.043 ± 0.937  ops/ms
o.k.c.h.b.netty.NettyBenchmark.hello            thrpt    5  66.252 ± 0.505  ops/ms
o.k.c.h.b.colossus.ColossusBenchmark.hello      thrpt    5  63.126 ± 1.389  ops/ms
o.k.c.h.b.vertx.VertxBenchmark.hello            thrpt    5  59.839 ± 0.818  ops/ms```
o.k.c.h.b.jetty.JettyBenchmark.hello            thrpt    5  56.719 ± 1.922  ops/ms
o.k.c.h.b.grizzly.GrizzlyBenchmark.hello        thrpt    5  51.892 ± 1.361  ops/ms
o.k.c.h.b.spark.SparkBenchmark.hello            thrpt    5  48.565 ± 0.765  ops/ms
o.k.c.h.b.ktor.KtorJettyBenchmark.hello         thrpt    5  42.163 ± 0.840  ops/ms
o.k.c.h.b.akka.AkkaHttpBenchmark.hello          thrpt    5  39.109 ± 1.636  ops/ms
o.k.c.h.b.ktor.KtorNettyBenchmark.hello         thrpt    5  36.553 ± 0.924  ops/ms
o.k.c.h.b.nanohttpd.NanoHttpBenchmark.hello     thrpt    5  20.401 ± 0.719  ops/ms
o.k.c.h.b.ninjaframework.NinjaBenchmark.hello   thrpt    5  12.194 ± 0.210  ops/ms
o.k.c.h.b.fluenthttp.FluentHttpBenchmark.hello  thrpt    5   1.207 ± 0.036  ops/ms

## Thanks
Based on prior work by [rocketraman](https://github.com/rocketraman/kotlin-web-hello-world)
