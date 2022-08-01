package ru.job4j.design.parking;

public class CargoCar3d implements ICar {
    private final int size;

    public CargoCar3d(int size) {
        if (size <= PassCar3d.SIZE) {
            throw new IllegalArgumentException("Size must be greater than 1");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
