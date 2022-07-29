package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Warehouse implements Store {

    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean addFood(Food food) {
        boolean b = false;
        Objects.requireNonNull(food);
        double term = termControl(food);
        if (term < WAREHAUS_TERM) {
            foods.add(food);
            b = true;
        }
        return b;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream().filter(filter).toList();
    }
}
