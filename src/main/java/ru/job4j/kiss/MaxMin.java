package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T maxMin(List<T> value, Comparator<T> comparator) {
        T maxMin;
        if (value.isEmpty()) {
            maxMin = null;
        } else {
            maxMin = value.get(0);
            for (T t : value) {
                if (comparator.compare(t, maxMin) > 0) {
                    maxMin = t;
                }
            }
        }
        return maxMin;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return max(value, comparator.reversed());
    }
}

class Main {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 34, 43, 1, -100);
        Comparator<Integer> comparator = Integer::compareTo;
        MaxMin maxMin = new MaxMin();
        Integer max = maxMin.max(integers, comparator);
        Integer min = maxMin.min(integers, comparator);
        System.out.println("Max is " + max);
        System.out.println("Min is " + min);
    }
}