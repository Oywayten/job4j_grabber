package ru.job4j.design.foodstore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ControlQualityTest {
    static ControlQuality controlQuality = new ControlQuality();
    static Store warh = new Warehouse();
    static Store shop = new Shop();
    static Store trash = new Trash();
    DataGetSet apple = new Apple("NewApple",
            new GregorianCalendar(2022, Calendar.DECEMBER, 12),
            new GregorianCalendar(2022, Calendar.JULY, 27), 100, 10);
    DataGetSet fish = new Fish("Fish",
            new GregorianCalendar(2022, Calendar.AUGUST, 27),
            new GregorianCalendar(2022, Calendar.JUNE, 27), 400, 20);
    DataGetSet meat = new Meat("Meat",
            new GregorianCalendar(2022, Calendar.JULY, 30),
            new GregorianCalendar(2022, Calendar.JUNE, 27), 500, 15);
    DataGetSet secondFish = new Fish("SecondFish",
            new GregorianCalendar(2022, Calendar.JULY, 26),
            new GregorianCalendar(2022, Calendar.JUNE, 27), 400, 20);
    List<DataGetSet> foodList = List.of(apple, fish, meat, secondFish);

    @BeforeAll
    static void addStore() {
        controlQuality.addStore(warh);
        controlQuality.addStore(shop);
        controlQuality.addStore(trash);
    }

    @Test
    void whenAppleToWarehouseFishToShopMeatToShopAppleToTrash() {
        for (DataGetSet f : foodList) {
            double term = controlQuality.termControl(f);
            controlQuality.separate(f, term);
        }
        assertThat(warh.findBy(set -> true)).isEqualTo(List.of(apple));
        assertThat(shop.findBy(set -> true)).isEqualTo(List.of(fish, meat));
        assertThat(shop.findBy(set -> true).get(1).getPrice()).isEqualTo(575);
        assertThat(warh.findBy(set -> true)).isEqualTo(List.of(apple));
    }

    @Test
    void whenNullIsNpe() {
        assertThatNullPointerException().isThrownBy(() -> {
            controlQuality.termControl(null);
        });
    }
}