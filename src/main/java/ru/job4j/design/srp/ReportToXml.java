package ru.job4j.design.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс сериализует в XML
 */
public class ReportToXml implements Report, Output {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private final Store store;

    public ReportToXml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws Exception {
        List<Employee> employeeList = store.findBy(filter);
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String generate;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employeeList), writer);
            generate = writer.getBuffer().toString();
        }
        return generate;
    }

    @Override
    public void write(String report) {
        try {
            Files.writeString(Path.of("src/main/java/ru/job4j/design/srp/report.xml"), report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    public static class Employees {
        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> list) {
            this.employees = list;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Employees{");
            sb.append("employees=").append(employees);
            sb.append('}');
            return sb.toString();
        }
    }
}