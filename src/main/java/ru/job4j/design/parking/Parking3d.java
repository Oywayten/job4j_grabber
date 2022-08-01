package ru.job4j.design.parking;

import java.util.Objects;

public class Parking3d implements IParking {
    private static final int ZERO = 0;
    private final ICar[] passPlaces;
    private final ICar[] cargoPlaces;
    private int passCursor;
    private int cargoCursor;

    public Parking3d(int passSize, int cargoSize) {
        if (passSize == ZERO && cargoSize == ZERO) {
            throw new IllegalArgumentException("Can't have parking without spaces");
        }
        passPlaces = new ICar[passSize];
        cargoPlaces = new ICar[cargoSize];
    }

    @Override
    public boolean carParking(ICar car) {
        int size = car.getSize();
        boolean b = false;
        Objects.requireNonNull(car);
        if (size > PassCar3d.SIZE
                && cargoCursor < cargoPlaces.length) {
            cargoPlaces[cargoCursor++] = car;
            b = true;
        } else if (passCursor <= passPlaces.length - size) {
            passPlaces[passCursor] = car;
            passCursor = passCursor + size;
            b = true;
        }
        return b;
    }
}
