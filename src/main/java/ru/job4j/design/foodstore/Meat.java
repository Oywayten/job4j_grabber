package ru.job4j.design.foodstore;

import java.util.Calendar;

public class Meat extends Food {

    public Meat(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Calendar getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public Calendar getExpiryDate() {
        return super.getExpiryDate();
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }

    @Override
    public int getDiscount() {
        return super.getDiscount();
    }
}
