package ru.job4j.design.parking;

public class Iparking3d implements IParking {
    private final int[] places;
    private boolean isCargo;

    public Iparking3d(int size, boolean isCargo) {
        places = new int[size];
    }

    @Override
    public boolean carParking(ICar car) {
        return false;
    }

    @Override
    public int getFree() {
        return 0;
    }

    @Override
    public boolean isCargo() {
        return false;
    }
}
