package ru.job4j.diperror;

import java.util.ArrayList;
import java.util.List;

public class DogOwner {
    private String name;
    private String age;
    private final List<Dog> dogs;
    
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

/*
1. реализация зависит от конкретного хранилища собак - списка
2. зависимость оо конкретной реализации животного - собаки. если будет кошка или
енот, то программа уже не сможет работать. поэтому надо создать интерфейс Pet и его уже реализовывать
в классах Dog, Cat etc. а в нашем коде должен быть не Dog, а Pet. соответственно методы будут не на получение имени соьаки6, а вообще имени животного.

 */