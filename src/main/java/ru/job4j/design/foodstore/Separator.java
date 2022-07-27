package ru.job4j.design.foodstore;

import java.util.List;

public interface Separator {

    void separate(DataGetSet food, double term);

    List<Store> getStores();

    void addStore(Store store);
}
