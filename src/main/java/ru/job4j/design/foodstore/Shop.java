package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Shop implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean addFood(Food food) {
        boolean b = false;
        Objects.requireNonNull(food);
        double term = termControl(food);
        if (term >= WAREHAUS_TERM && term <= DISCOUNT_TERM) {
            foods.add(food);
            b = true;
        } else if (term > DISCOUNT_TERM && term < TRASH_TERM) {
            setDiscountPrice(food);
            foods.add(food);
            b = true;
        }
        return b;
    }

    public void setDiscountPrice(Food food) {
        double price = food.getPrice() + (food.getPrice() / 100 * food.getDiscount());
        food.setPrice(price);
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream().filter(filter).toList();
    }
}
