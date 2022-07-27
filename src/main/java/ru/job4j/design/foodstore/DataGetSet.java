package ru.job4j.design.foodstore;

import java.util.Calendar;

public interface DataGetSet {

    String getName();

    Calendar getCreateDate();

    Calendar getExpiryDate();

    double getPrice();

    void setDiscountPrice();

    int getDiscount();
}
