package ru.job4j.design.srp;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Store3d implements Store {

    List<Employee> employees;

    public Store3d(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        List<Employee> list = new LinkedList<>();
        for (Employee employee : employees) {
            if (filter.test(employee)) {
                list.add(employee);
            }
        }
        return list;
    }
}
