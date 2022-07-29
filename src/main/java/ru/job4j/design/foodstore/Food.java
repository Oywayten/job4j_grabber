package ru.job4j.design.foodstore;

import java.util.Calendar;

public abstract class Food {
    private final String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private final int discount;
    private double price;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }
}
