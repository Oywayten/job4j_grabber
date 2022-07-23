package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineTest {

    Employee ivan =
            new Employee("Ivan", new GregorianCalendar(2022, Calendar.JANUARY, 1),
                    new GregorianCalendar(2022, Calendar.JULY, 1), 100_000);
    Employee andrew =
            new Employee("Andrew", new GregorianCalendar(2022, Calendar.JANUARY, 15),
                    new GregorianCalendar(2022, Calendar.JULY, 15), 90_000);
    Employee bob =
            new Employee("Bob", new GregorianCalendar(2022, Calendar.JANUARY, 31),
                    new GregorianCalendar(2022, Calendar.JULY, 31), 80_000);
    List<Employee> list = Arrays.asList(andrew, bob, ivan);
    ReportEngineHtml engine = new ReportEngineHtml(new Store3d(list));

    @Test
    void whenEmployeesSortedBySalaryDescThenIvanAndrewBob() {
        List<Employee> expected = Arrays.asList(ivan, andrew, bob);
        engine.sortSalaryDesc(list);
        assertThat(list).isEqualTo(expected);
    }

    @Test
    void whenSalaryInUsd() {
        assertThat(ivan.getSalary()).isEqualTo(100_000d / 50);
    }

    @Test
    void whenReportIsHtml() {
        String generate = engine.generate(employee -> employee.getSalary() > 1_000);
        engine.write(generate);
        String result = "";
        try {
            result = Files.readString(Path.of("src/main/java/ru/job4j/design/srp/report.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder expected = new StringBuilder();
        expected.append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("    <meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("    <title>Title</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name;Salary;")
                .append(System.lineSeparator())
                .append("Ivan;").append(ivan.getSalary()).append(";").append(System.lineSeparator())
                .append("Andrew;").append(andrew.getSalary()).append(";").append(System.lineSeparator())
                .append("Bob;").append(bob.getSalary()).append(";").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }
}