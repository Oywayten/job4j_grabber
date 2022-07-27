package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements Store {

    private final List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream().filter(filter).toList();
    }

}
