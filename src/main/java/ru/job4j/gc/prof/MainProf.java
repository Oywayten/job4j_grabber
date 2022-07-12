package ru.job4j.gc.prof;

import java.util.Random;

public class MainProf {
    public static void main(String[] args) {
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RandomArray array = new RandomArray(new Random());
        array.insert(250_000);
//       new MergeSort().sort(array);
//       new InsertSort().sort(array);
//       new BubbleSort().sort(array);

    }
}
