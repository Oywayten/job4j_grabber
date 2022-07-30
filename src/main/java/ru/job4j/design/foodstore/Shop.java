package ru.job4j.design.foodstore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Класс хранилища Магазин
 */
public class Shop implements Store {
    private static final int WAREHAUS_TERM = 25;
    private static final int DISCOUNT_TERM = 75;
    private static final int TRASH_TERM = 100;
    private final List<Food> foods = new ArrayList<>();

    /**
     * Метод добавляет продукт в магазин если срок продукта от 25 до 75 %%.
     * Если срок вышел больше 75%, но не законился, то устанавливается цена со скидкой, после чего продукт
     * тоже добавляется в магазин.
     * @param food - продукт для добавления на склад
     * @return возвращает true, если продукт был добавлен на склад
     */
    @Override
    public boolean addFood(Food food) {
        boolean b = false;
        Objects.requireNonNull(food);
        double term = termControl(food);
        if (term >= WAREHAUS_TERM && term <= DISCOUNT_TERM) {
            foods.add(food);
            b = true;
        } else if (term > DISCOUNT_TERM && term < TRASH_TERM) {
            setDiscountPrice(food);
            foods.add(food);
            b = true;
        }
        return b;
    }

    /**
     * Приватный метод для установки цены со скидкой на продукт
      * @param food продукт, для которого надо установить цену со скидкой
     */
    private void setDiscountPrice(Food food) {
        double price = food.getPrice() + (food.getPrice() / 100 * food.getDiscount());
        food.setPrice(price);
    }

    /**
     * Возвращает список продуктов со склада фильтрованных по Predicate
     * @param filter переданный Predicate для фильтрации
     * @return список продуктов типа List<Food>
     */
    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream().filter(filter).toList();
    }
}
