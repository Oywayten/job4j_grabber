package ru.job4j.srperror;

import java.util.ArrayList;
import java.util.List;

public final class ItemCreator {

    private static ItemCreator itemCreator = null;

    private final List<Item> items = new ArrayList<>();

    private ItemCreator() {
    }

    public static ItemCreator getItemCreator() {
        if (itemCreator == null) {
            itemCreator = new ItemCreator();
        }
        return itemCreator;
    }

    public void addItem(String name, String text) {
        items.add(new Item(name, text));
    }

    public List<Item> getItems() {
        return items;
    }

    public int volume() {
        return items.size();
    }

    public void show() {
        System.out.println(items);
    }
}
