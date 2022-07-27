package ru.job4j.design.foodstore;

import java.util.Calendar;

public class Fish implements DataGetSet {
    Food food;

    public Fish(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        food = new Food(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String getName() {
        return food.getName();
    }

    @Override
    public Calendar getCreateDate() {
        return food.getCreateDate();
    }

    @Override
    public Calendar getExpiryDate() {
        return food.getExpiryDate();
    }

    @Override
    public double getPrice() {
        return food.getPrice();
    }

    @Override
    public int getDiscount() {
        return food.getDiscount();
    }

    @Override
    public void setDiscountPrice() {
        food.setDiscountPrice();
    }
}
