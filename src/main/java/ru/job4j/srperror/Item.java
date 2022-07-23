package ru.job4j.srperror;

import java.time.LocalDateTime;

public class Item {
    private String name;
    private String text;
    private final LocalDateTime time;

    public Item(String name, String text) {
        this.name = name;
        this.text = text;
        this.time = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void show() {
        System.out.println("Subject: " + name + "\nText: " + text);
    }
}
