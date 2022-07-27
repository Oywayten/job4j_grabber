package ru.job4j.diperror;

import java.util.List;

public class DogOwner {
    private final List<Dog> dogs;
    private String name;
    private String age;

    public DogOwner(List<Dog> dogs) {
        this.dogs = dogs;
    }

    void playWithDog(String playName) {
        System.out.println("Play " + playName);
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getDogName(int index) {
        return dogs.get(index).getName();
    }

    public void showDogsNames() {
        System.out.println("Dogs name is: ");
        for (Dog d : dogs) {
            System.out.println(d);
        }
    }
}