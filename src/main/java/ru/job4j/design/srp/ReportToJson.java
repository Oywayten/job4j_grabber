package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс сериализует в JSON
 */
public class ReportToJson implements Report, Output {

    private final Store store;

    public ReportToJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employeeList = store.findBy(filter);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(employeeList);
    }

    @Override
    public void write(String report) {
        try {
            Files.writeString(Path.of("src/main/java/ru/job4j/design/srp/report.json"), report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}