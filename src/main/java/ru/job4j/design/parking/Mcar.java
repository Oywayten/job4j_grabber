package ru.job4j.design.parking;

public class Mcar implements Icar {
    int size;

    @Override
    public int parkIn() {
        return size;
    }
}
