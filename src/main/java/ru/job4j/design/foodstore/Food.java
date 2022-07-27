package ru.job4j.design.foodstore;

import java.util.Calendar;

public class Food implements DataGet {
    private final String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private final double price;
    private final int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Calendar getCreateDate() {
        return createDate;
    }

    @Override
    public Calendar getExpiryDate() {
        return expiryDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getDiscount() {
        return discount;
    }
}