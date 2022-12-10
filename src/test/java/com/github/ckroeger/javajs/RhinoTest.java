package com.github.ckroeger.javajs;

import org.junit.jupiter.api.Test;
import org.mozilla.javascript.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

class RhinoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RhinoTest.class);

    Rhino uut = new Rhino();

    @Test
    void test_execPower() {
        Object result = uut.execPower(3);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(9.0);
        String report = "f(3) = " + Context.toString(result);
        LOGGER.info(report);

    }

    @Test
    void exec() {
        Object result = uut.exec(3);
        assertThat(result).isNotNull();
    }

    @Test
    void test_execDynamic() {
        Object result = uut.execDynamic(3);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(3.0);
        String report = "f(x) = " + Context.toString(result);
        LOGGER.info(report);

    }

}