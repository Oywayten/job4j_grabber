package ru.job4j.design.foodstore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ControlQualityTest {
    static Food apple;
    static Food fish;
    static Food meat;
    static Food secondFish;
    static List<Food> foodList;
    Store warh = new Warehouse();
    Store shop = new Shop();
    Store trash = new Trash();
    List<Store> storeList = new ArrayList<>(List.of(warh, shop, trash));
    ControlQuality controlQuality =
            new ControlQuality(storeList);

    @BeforeAll
    static void setup() {
        Calendar createApple = Calendar.getInstance();
        Calendar expiryApple = Calendar.getInstance();
        expiryApple
                .set(expiryApple.get(Calendar.YEAR),
                        expiryApple.get(Calendar.MONTH), expiryApple.get(Calendar.DAY_OF_MONTH) + 8,
                        10, 0);
        createApple
                .set(createApple.get(Calendar.YEAR),
                        createApple.get(Calendar.MONTH), createApple.get(Calendar.DAY_OF_MONTH) - 1,
                        10, 0);
        apple = new Apple("NewApple", expiryApple, createApple, 100, 10);
        Calendar creatFish = Calendar.getInstance();
        Calendar expiryFish = Calendar.getInstance();
        expiryFish.set(
                expiryFish.get(Calendar.YEAR),
                expiryFish.get(Calendar.MONTH),
                expiryFish.get(Calendar.DAY_OF_MONTH) + 8,
                10, 0);
        creatFish.set(
                creatFish.get(Calendar.YEAR),
                creatFish.get(Calendar.MONTH),
                creatFish.get(Calendar.DAY_OF_MONTH) - 8,
                10, 0);
        fish = new Fish("Fish", expiryFish, creatFish, 400, 20);
        Calendar creatMeat = Calendar.getInstance();
        Calendar expiryMeat = Calendar.getInstance();
        expiryMeat.set(
                expiryMeat.get(Calendar.YEAR),
                expiryMeat.get(Calendar.MONTH),
                expiryMeat.get(Calendar.DAY_OF_MONTH) + 2,
                10, 0);
        creatMeat.set(
                creatMeat.get(Calendar.YEAR),
                creatMeat.get(Calendar.MONTH),
                creatMeat.get(Calendar.DAY_OF_MONTH) - 7,
                10, 0);
        meat = new Meat("Meat", expiryMeat, creatMeat, 500, 15);
        Calendar creatSecondFish = Calendar.getInstance();
        Calendar expirySecondFish = Calendar.getInstance();
        expirySecondFish.set(
                expirySecondFish.get(Calendar.YEAR),
                expirySecondFish.get(Calendar.MONTH),
                expirySecondFish.get(Calendar.DAY_OF_MONTH) - 2,
                10, 0);
        creatSecondFish.set(
                creatSecondFish.get(Calendar.YEAR),
                creatSecondFish.get(Calendar.MONTH),
                creatSecondFish.get(Calendar.DAY_OF_MONTH) - 7,
                10, 0);
        secondFish = new Fish("SecondFish", expirySecondFish, creatSecondFish, 400, 20);
        foodList = List.of(apple, fish, meat, secondFish);
    }

    @Test
    void whenAppleToWarehouseFishToShopMeatToShopAppleToTrash() {
        for (Food f : foodList) {
            controlQuality.separate(f);
        }
        assertThat(warh.findBy(set -> true)).isEqualTo(List.of(apple));
        assertThat(shop.findBy(set -> true)).isEqualTo(List.of(fish, meat));
        assertThat(shop.findBy(set -> true).get(1).getPrice()).isEqualTo(575);
        assertThat(warh.findBy(set -> true)).isEqualTo(List.of(apple));
    }

    @Test
    void whenNullIsNpe() {
        assertThatNullPointerException().isThrownBy(() -> controlQuality.separate(null));
    }
}