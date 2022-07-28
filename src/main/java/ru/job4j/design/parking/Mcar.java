package ru.job4j.design.parking;

public class Mcar implements ICar {
    int size;

    public Mcar(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
