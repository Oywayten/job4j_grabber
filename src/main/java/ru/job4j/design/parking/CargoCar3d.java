package ru.job4j.design.parking;

public class CargoCar3d implements ICar {
    private final int size;
    private static final int CONST = 1;

    public CargoCar3d(int size) {
        if (size <= CONST) {
            throw new IllegalArgumentException("Size must be greater than 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
