package ru.job4j.design.parking;

public class Parking3d implements IParking {
    private final int[] passPlaces;
    private final int[] cargoPlaces;
    private static final int ZERO = 0;

    public Parking3d(int passSize, int cargoSize) {
        if (passSize == ZERO && cargoSize == ZERO) {
            throw new IllegalArgumentException("Can't have parking without spaces");
        }
        passPlaces = new int[passSize];
        cargoPlaces = new int[cargoSize];
    }

    @Override
    public boolean carParking(ICar car) {
        return false;
    }
}
