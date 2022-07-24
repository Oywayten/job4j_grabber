package ru.job4j.lsperror;

import java.util.Calendar;

public class Car {
    private int wheels;
    private final int passengers = 3;
    private Calendar dateNextWash;

    public Car() {
        dateNextWash = Calendar.getInstance();
        dateNextWash.add(Calendar.DAY_OF_WEEK, 7);
    }

    public void setWheels(int wheels) {
        if (wheels < 4 || wheels % 2 != 0) {
            throw new IllegalArgumentException("Wheels must be an even number and more than 4");
        }
        this.wheels = wheels;
    }

    public void setNewDateNextWash(Calendar calendar) {
        if (calendar.getTime().getDate() <= dateNextWash.getTime().getDate()) {
            throw new IllegalArgumentException("Choose a date in the future");
        }
        dateNextWash = calendar;
    }

    public int getPassengers() {
        return passengers;
    }
}
