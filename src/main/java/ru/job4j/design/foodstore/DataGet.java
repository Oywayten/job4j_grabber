package ru.job4j.design.foodstore;

import java.util.Calendar;

public interface DataGet {

    String getName();

    Calendar getCreateDate();

    Calendar getExpiryDate();

    double getPrice();

    int getDiscount();
}
