package ru.job4j.design.foodstore;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public interface Store {

    boolean addFood(Food food);

    List<Food> findBy(Predicate<Food> filter);

    void clear();

    default double termControl(Food food) {
        Objects.requireNonNull(food);
        double fullTerm = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double passedTerm = Calendar.getInstance().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double ratioTerm = passedTerm / fullTerm;
        return ratioTerm * 100;
    }
}
