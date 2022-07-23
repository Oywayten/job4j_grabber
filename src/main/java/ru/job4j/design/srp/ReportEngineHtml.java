package ru.job4j.design.srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngineHtml implements Report, Sort, Output {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportEngineHtml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("    <meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("    <title>Title</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name;Salary;")
                .append(System.lineSeparator());
        List<Employee> employeeList = store.findBy(filter);
        sortSalaryDesc(employeeList);
        for (Employee employee : employeeList) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return text.toString();
    }

    @Override
    public void sortSalaryDesc(List<Employee> list) {
        Comparator<Employee> comparator = Comparator.comparingDouble(Employee::getSalary);
        list.sort(comparator.reversed());
    }

    @Override
    public void write(String report) {
        try {
            Files.writeString(Path.of("src/main/java/ru/job4j/design/srp/report.html"), report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}