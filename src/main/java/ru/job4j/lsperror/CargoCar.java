package ru.job4j.lsperror;

import java.util.Calendar;

public class CargoCar extends Car {
    private int wheels;
    private Calendar dateNextWash;

    public CargoCar() {
        super();
    }

    public void setWheels(int wheels) {
        if (wheels != 6) {
            throw new IllegalArgumentException("Wheels must be an even number and more than 4");
        }
        this.wheels = wheels;
    }

    public void setNewDateNextWash(Calendar calendar) {
        dateNextWash = calendar;
    }
}
