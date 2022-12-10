package com.github.ckroeger.javajs;

import org.junit.jupiter.api.Test;
import org.mozilla.javascript.Context;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
@SuppressWarnings("java:S5786")
public class RhinoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RhinoTest.class);




    @Test
    void test_execPower() {
        Rhino uut = new Rhino();
        Object result = checkResult(uut.execPower(3), 9.0);
        String report = "f(3) = " + Context.toString(result);
        LOGGER.info(report);

    }

    @Test
    void exec() {
        Rhino uut = new Rhino();
        Object result = uut.exec(3);
        assertThat(result).isNotNull();
    }

    @Test
    void test_execDynamic() {
        Rhino uut = new Rhino();
        Object result = checkResult(uut.execDynamic(3), 3.0);
        String report = "f(x) = " + Context.toString(result);
        LOGGER.info(report);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void it_execDynamic() {
        Rhino uut = new Rhino();
        checkResult(uut.execDynamic(9), 9.0);
    }

    private Object checkResult(Object result, double expected) {
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expected);
        return result;
    }
}