package ru.job4j.design.parking;

import java.util.ArrayList;
import java.util.List;

public class CarsStore3d implements CarsStore {
    private final List<IParking> list = new ArrayList<>();

    @Override
    public List<IParking> getStorage() {
        return list;
    }
}
