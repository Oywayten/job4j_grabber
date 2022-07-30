package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Класс хранилища Склад для продуктов
 */
public class Warehouse implements Store {
    private static final int WAREHAUS_TERM = 25;
    private final List<Food> foods = new ArrayList<>();

    /**
     * Метод добавляет продукт на склад если срок продукта вышел менее чем на 25%
     * @param food - продукт для добавления на склад
     * @return возвращает true, если продукт был добавлен на склад
     */
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

    /**
     * Возвращает список продуктов со склада фильтрованных по Predicate
     * @param filter переданный Predicate для фильтрации
     * @return список продуктов типа List<Food>
     */
    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream().filter(filter).toList();
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
