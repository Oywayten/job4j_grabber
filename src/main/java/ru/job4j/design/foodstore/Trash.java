package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Класс хранилища Мусорная корзина для продуктов
 */
public class Trash implements Store {
    private static final int TRASH_TERM = 100;
    private final List<Food> foods = new ArrayList<>();

    /**
     * Метод добавляет продукт в корзину если срок хранения закончился
     * @param food - продукт для добавления в корзину
     * @return возвращает true, если продукт был добавлен в корзину
     */
    @Override
    public boolean addFood(Food food) {
        boolean b = false;
        Objects.requireNonNull(food);
        double term = termControl(food);
        if (term >= TRASH_TERM) {
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

}
