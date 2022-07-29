package ru.job4j.design.foodstore;

import java.util.List;

public class ControlQuality implements Separator {

    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    @Override
    public void separate(Food food) {
        for (Store s : storeList) {
            boolean b = s.addFood(food);
            if (b) {
                break;
            }
        }
    }
}

