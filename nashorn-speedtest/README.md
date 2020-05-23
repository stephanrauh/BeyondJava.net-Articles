# Benchmarking Truffle (GraalVM) vs. Nashorn vs. Rhino

Inspired by the article [Nashorn: The New Rhino on the Block](https://ariya.io/2014/03/nashorn-the-new-rhino-on-the-block), this benchmark compares the performance of the three popular JavaScript engine of the Java universe:

* Truffle, the polyglot engine of GraalVM
* Nashorn, the JavaScript engine that landed with Java 8
* Rhino, the Mozilla project that's a lot older but that's still being maintained
* and Node.js. That's not Java, but it's good to know which level of performance you could get if you can choose freely.

## The test scenario

The test uses a JavaScript parser called [esprima](https://esprima.org/) to tokenize the minified build of jQuery 3.5.1.

Of course, that's a far cry from being a scientific benchmark, but it has several interesting traits:

* it deals massively with strings
* it keeps the garbage collector busy
* it uses non-linear code execution (recursive-descent parsing).

## My results
On GraalVM 20.1.0, Truffle is the fastest engine, followed by Nashorn and Rhino. To my surprise, it's even faster than native node.js 12.4.1, at least in the long run. There's a severe cold start penalty. It takes GraalVM more than a hundred iterations to overtake node.js. The additional overhead of the [Futamura projection](https://www.beyondjava.net/truffle-compiler-compiler) shows.

| iteration | Truffle | Nashorn |   Rhino | native node.js |
| --------: | ------: | ------: | ------: | -------------: |
|        #1 | 2276 ms | 4170 ms | 1595 ms |         177 ms |
|        #2 |  872 ms | 1220 ms |  803 ms |          89 ms |
|       #10 |  398 ms |  238 ms |  403 ms |          59 ms |
|       #20 |  161 ms |  144 ms |  366 ms |          54 ms |
|       #50 |  115 ms |  107 ms |  344 ms |          63 ms |
|      #100 |   63 ms |  101 ms |  358 ms |          50 ms |
|      #150 |   44 ms |  100 ms |  344 ms |          50 ms |
|      #200 |   43 ms |   99 ms |  340 ms |          65 ms |
|      #250 |   42 ms |  101 ms |  344 ms |          65 ms |
|      #300 |   43 ms |  142 ms |  354 ms |          48 ms |

Did I sell Truffle successfully to you? Well, I just compared the performance of the engines on the GraalVM. In the real world, you're probably using one of the good old standard JDKs. So I ran the benchmark again with AdoptOpenJDK 11.0.5. Of course, there's not Truffle. But in the long run, Nashorn almost reaches the performance of both node.js and Truffle.

| iteration | Truffle | Nashorn |   Rhino | native node.js |
| --------: | ------: | ------: | ------: | -------------: |
|        #1 |     n/a | 5194 ms | 1365 ms |         177 ms |
|        #2 |     n/a | 1601 ms |  650 ms |          89 ms |
|       #10 |     n/a |  285 ms |  446 ms |          59 ms |
|       #20 |     n/a |  216 ms |  390 ms |          54 ms |
|       #50 |     n/a |   71 ms |  400 ms |          63 ms |
|      #100 |     n/a |  118 ms |  386 ms |          50 ms |
|      #150 |     n/a |   64 ms |  400 ms |          50 ms |
|      #200 |     n/a |   64 ms |  388 ms |          65 ms |
|      #250 |     n/a |   86 ms |  375 ms |          65 ms |
|      #300 |     n/a |   65 ms |  375 ms |          48 ms |

I've also run the benchmark with AdoptOpenJDK 14.0.5. For some reason, Java 14 seems to be slower than Java 11. Maybe the LTS version are heavily optimized, while development on the other version focuses on features.

## How to run the benchmark

The project is a standard Maven project. It contains a single class, `Speedtest.java`, which contains a `main` method. So running the benchmark should be just a matter of importing it to your IDE and running the `main` method.

The entire test suite is contained in the file `benchmark.sh`. That's Unix script, and it reflects the Java versions I had installed when I ran the test. If you want to use it, you just have to modify the path(s) to your JDK(s).