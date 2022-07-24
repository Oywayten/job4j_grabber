package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * Класс создает отчет с измененным видом зарплаты
 */
public class ReportForAccountants implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    private static final int EXCHANGE_RATES = 50;
    private static final String CURRENCY_NAME = " usd;";

    public ReportForAccountants(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary() / EXCHANGE_RATES).append(CURRENCY_NAME)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}