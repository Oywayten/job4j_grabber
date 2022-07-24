package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Отчет выводит сотрудников в порядке убывания зарплаты и без даты найма и увольнения
 */
public class ReportForHr implements Report, Sort {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportForHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Salary;")
                .append(System.lineSeparator());
        List<Employee> employeeList = store.findBy(filter);
        sortSalaryDesc(employeeList);
        for (Employee employee : employeeList) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    @Override
    public void sortSalaryDesc(List<Employee> list) {
        Comparator<Employee> comparator = Comparator.comparingDouble(Employee::getSalary);
        list.sort(comparator.reversed());
    }
}