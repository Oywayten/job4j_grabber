package ru.job4j.kiss;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

class MaxMinTest {
    List<Integer> integers = List.of(1, 34, 43, 1, -100);
    Comparator<Integer> comparator = Integer::compareTo;
    MaxMin maxMin = new MaxMin();

    @Test
    void when43IsMax() {
        int expected = 43;
        int result = maxMin.max(integers, comparator);
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void whenMinus100IsMin() {
        int expected = -100;
        int result = maxMin.min(integers, comparator);
        assertThat(expected).isEqualTo(result);
    }
}