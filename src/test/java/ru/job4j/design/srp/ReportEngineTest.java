package ru.job4j.design.srp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineTest {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private static final Employee IVAN =
            new Employee("Ivan", new GregorianCalendar(2022, Calendar.JANUARY, 1),
                    new GregorianCalendar(2022, Calendar.JULY, 1), 100_000);
    private static final Employee ANDREW =
            new Employee("Andrew", new GregorianCalendar(2022, Calendar.JANUARY, 15),
                    new GregorianCalendar(2022, Calendar.JULY, 15), 90_000);
    private static final Employee BOB =
            new Employee("Bob", new GregorianCalendar(2022, Calendar.JANUARY, 31),
                    new GregorianCalendar(2022, Calendar.JULY, 31), 80_000);
    private static final MemStore MEM_STORE = new MemStore();

    @BeforeAll
    private static void addEm() {
        MEM_STORE.add(ANDREW);
        MEM_STORE.add(BOB);
        MEM_STORE.add(IVAN);
    }

    @Test
    public void whenOldGenerated() throws Exception {
        Report engine = new ReportEngine(MEM_STORE);
        String expect = """
                Name; Hired; Fired; Salary;
                Andrew;15:01:2022 00:00;15:07:2022 00:00;90000.0;
                Bob;31:01:2022 00:00;31:07:2022 00:00;80000.0;
                Ivan;01:01:2022 00:00;01:07:2022 00:00;100000.0;
                """;
        assertThat(engine.generate(em -> true)).isEqualToNormalizingNewlines(expect);
    }

    @Test
    public void whenReportForProgrammers() {
        ReportForProgrammers engine = new ReportForProgrammers(MEM_STORE);
        String generate = engine.generate(employee -> true);
        engine.write(generate);
        String result = "";
        try {
            result = Files.readString(Path.of("src/main/java/ru/job4j/design/srp/report.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Title</title>
                </head>
                <body>
                Name; Hired; Fired; Salary;<br>
                Andrew;15:01:2022 00:00;15:07:2022 00:00;90000.0;<br>
                Bob;31:01:2022 00:00;31:07:2022 00:00;80000.0;<br>
                Ivan;01:01:2022 00:00;01:07:2022 00:00;100000.0;<br>
                </body>
                </html>
                """;
        assertThat(result).isEqualToNormalizingNewlines(expected);
    }

    @Test
    public void whenReportForHr() {
        ReportForHr engine = new ReportForHr(MEM_STORE);
        String result = engine.generate(employee -> true);
        StringBuilder expected = new StringBuilder();
        expected.append("Name;Salary;")
                .append(System.lineSeparator())
                .append("Ivan;").append(IVAN.getSalary()).append(";").append(System.lineSeparator())
                .append("Andrew;").append(ANDREW.getSalary()).append(";").append(System.lineSeparator())
                .append("Bob;").append(BOB.getSalary()).append(";").append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportForAccountants() {
        ReportForAccountants engine = new ReportForAccountants(MEM_STORE);
        String result = engine.generate(employee -> employee.getSalary() > 1_000);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("Andrew;").append(DATE_FORMAT.format(ANDREW.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(ANDREW.getFired().getTime())).append(";")
                .append(ANDREW.getSalary() / ReportForAccountants.EXCHANGE_RATES)
                .append(ReportForAccountants.CURRENCY_NAME).append(System.lineSeparator())
                .append("Bob;").append(DATE_FORMAT.format(BOB.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(BOB.getFired().getTime())).append(";")
                .append(BOB.getSalary() / ReportForAccountants.EXCHANGE_RATES)
                .append(ReportForAccountants.CURRENCY_NAME).append(System.lineSeparator())
                .append("Ivan;").append(DATE_FORMAT.format(IVAN.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(IVAN.getFired().getTime())).append(";")
                .append(IVAN.getSalary() / ReportForAccountants.EXCHANGE_RATES)
                .append(ReportForAccountants.CURRENCY_NAME).append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenReportToJson() {
        ReportToJson engine = new ReportToJson(MEM_STORE);
        String generate = engine.generate(employee -> true);
        engine.write(generate);
        String result = "";
        try {
            result = Files.readString(Path.of("src/main/java/ru/job4j/design/srp/report.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected =
                new StringBuilder()
                        .append("[{\"name\":\"Andrew\",")
                        .append("\"hired\":{\"year\":2022,\"month\":0,")
                        .append("\"dayOfMonth\":15,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                        .append("\"fired\":{\"year\":2022,\"month\":6,")
                        .append("\"dayOfMonth\":15,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                        .append("\"salary\":90000.0},")
                        .append("{\"name\":\"Bob\",\"hired\":{\"year\":2022,\"month\":0,")
                        .append("\"dayOfMonth\":31,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                        .append("\"fired\":{\"year\":2022,\"month\":6,")
                        .append("\"dayOfMonth\":31,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                        .append("\"salary\":80000.0},").append("{\"name\":\"Ivan\",")
                        .append("\"hired\":{\"year\":2022,\"month\":0,")
                        .append("\"dayOfMonth\":1,\"hourOfDay\":0,\"minute\":0,\"second\":0}")
                        .append(",\"fired\":{\"year\":2022,\"month\":6,")
                        .append("\"dayOfMonth\":1,\"hourOfDay\":0,\"minute\":0,\"second\":0},")
                        .append("\"salary\":100000.0}]")
                        .toString();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenReportToXml() throws Exception {
        ReportToXml engine = new ReportToXml(MEM_STORE);
        String generate = engine.generate(employee -> true);
        engine.write(generate);
        String result = "";
        try {
            result = Files.readString(Path.of("src/main/java/ru/job4j/design/srp/report.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employees>
                        <fired>2022-07-15T00:00:00+03:00</fired>
                        <hired>2022-01-15T00:00:00+03:00</hired>
                        <name>Andrew</name>
                        <salary>90000.0</salary>
                    </employees>
                    <employees>
                        <fired>2022-07-31T00:00:00+03:00</fired>
                        <hired>2022-01-31T00:00:00+03:00</hired>
                        <name>Bob</name>
                        <salary>80000.0</salary>
                    </employees>
                    <employees>
                        <fired>2022-07-01T00:00:00+03:00</fired>
                        <hired>2022-01-01T00:00:00+03:00</hired>
                        <name>Ivan</name>
                        <salary>100000.0</salary>
                    </employees>
                </employees>
                """;
        assertThat(result).isEqualToNormalizingNewlines(expected);
    }
}