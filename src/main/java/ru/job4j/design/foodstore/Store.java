package ru.job4j.design.foodstore;

import ru.job4j.design.srp.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Food food);

    List<Employee> findBy(Predicate<Food> filter);
}
