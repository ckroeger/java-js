package com.github.ckroeger.javajs;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import javax.script.ScriptException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("java:S5786")
public class NashornTest {

    @Test
    void exec() throws ScriptException, NoSuchMethodException {
        Nashorn uut = new Nashorn();
        assertThat(uut.exec()).isNotNull();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Test
    public void dynamic_exec() throws ScriptException {
        Nashorn uut = new Nashorn();
        assertThat(uut.dynamicExec()).isEqualTo(3.0);
    }
}