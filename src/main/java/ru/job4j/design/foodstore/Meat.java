package ru.job4j.design.foodstore;

import java.util.Calendar;

public class Meat implements DataGet {
    Food food;

    public Meat(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
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
}
