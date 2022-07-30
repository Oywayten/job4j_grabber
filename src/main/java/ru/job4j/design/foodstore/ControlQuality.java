package ru.job4j.design.foodstore;

import java.util.List;

/**
 * Класс выполняет контроль качества продуктов и их распределение по хранилищам.
 */
public class ControlQuality implements Separator {

    /**
     * Список хранилищ типа Store
     */
    private final List<Store> storeList;

    /**
     * Конструктор для создания экземпляра класса.
     * @param storeList списко хранилищ
     */
    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    /**
     * Метод в цикле добавляет продукт в каждое из храниилищ списка storeList,
     * если результат добавления s.addFood(food) == true, то цикл заканчивается.
     * @param food продукт для добавления в хранилище
     */
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

