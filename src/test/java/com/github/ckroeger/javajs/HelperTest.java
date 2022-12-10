package com.github.ckroeger.javajs;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HelperTest {

    Helper uut = new Helper();
    @Test
    void loadFromClassPath_with_non_existing_file() {
        Optional<String> result = uut.loadFromClassPath("NonExxistigFile");
        assertThat(result).isEmpty();
    }
    @Test
    void loadFromClassPath_with_existing_file() {
        Optional<String> result = uut.loadFromClassPath("/static/power.js");
        assertThat(result).isNotEmpty();
    }
}