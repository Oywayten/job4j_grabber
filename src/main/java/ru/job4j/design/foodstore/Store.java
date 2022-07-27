package ru.job4j.design.foodstore;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void addFood(DataGetSet food, double term);

    List<DataGetSet> findBy(Predicate<DataGetSet> filter);
}
