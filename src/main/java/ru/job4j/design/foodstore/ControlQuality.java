package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class ControlQuality implements Separator, ControlTerm {

    private final List<Store> storeList = new ArrayList<>();

    public ControlQuality() {
    }

    @Override
    public void separate(DataGetSet food, double term) {
        for (Store s : storeList) {
            s.addFood(food, term);
        }
    }

    @Override
    public void addStore(Store store) {
        storeList.add(store);
    }

    @Override
    public double termControl(DataGetSet food) {
        Objects.requireNonNull(food);
        double fullTerm = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double passedTerm = Calendar.getInstance().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double ratioTerm = passedTerm / fullTerm;
        return ratioTerm * 100;
    }
}

