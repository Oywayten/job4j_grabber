package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Warehouse implements Store {

    private final List<DataGetSet> foods = new ArrayList<>();

    @Override
    public void addFood(DataGetSet food, double term) {
        Objects.requireNonNull(food);
        if (term < 25) {
            foods.add(food);
        }
    }

    @Override
    public List<DataGetSet> findBy(Predicate<DataGetSet> filter) {
        return foods.stream().filter(filter).toList();
    }
}
