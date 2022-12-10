package com.github.ckroeger.javajs;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

class NashornBenchmarkIT {

    @Test
    @SuppressWarnings("squid:S2699")
    void benchmark_nashorn() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(NashornTest.class.getSimpleName())
                .warmupIterations(2)
                .measurementBatchSize(1)
                .measurementIterations(5)
                .operationsPerInvocation(100)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
