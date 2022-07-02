package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Класс выводит в консоль сообщение раз в 10 секунд
 */
public class AlertRabbit {
    private static Properties config;

    public static void main(String[] args) {
        setConfig();
        try (Connection cn = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
        )) {
            Class.forName(config.getProperty("driver-class-name"));
            /*
            Класс управляет всеми работами.
            Добавляем в Scheduler задачи, которые хотим выполнять периодически.
             */
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("cn", cn);
            /*
            Создание задачи.
            quartz каждый раз создает объект с типом org.quartz.Job
            Rabbit - класс, реализующий этот интерфейс (Job)
            В Rabbit описываем действия,которые надо выполнять execute()
             */
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            /*
            Создание расписания.
            Настраивает периодичность запуска.
            Раз в 10 секунд, из метода getInterval()
             */
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(getInterval())
                    .repeatForever();
            /*
            Задача выполняется через триггер.
            Указываем когда начинается задача.
            Запускаем сразу - startNow()
             */
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(11000);
            scheduler.shutdown();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    /**
     * Метод читает Properties из файла и сохраняет их в поле config
     */
    private static void setConfig() {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config = new Properties();
            config.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Метод читает интервал в секундах из config
     * @return возвращает величину интервала в секундах типа int
     */
    private static int getInterval() {
        return Integer.parseInt(config.getProperty("rabbit.interval"));
    }

    /**
     * В этом классе описываем требуемые действия: в нашем случае - вывод на консоль.
     */
    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        /**
         * Метод выполняет требуемые действия
         * @param context контекст в котором содержатся установленные в JobDataMap данные методом data.put(),
         *                получаем данные из контекста с помощью context.getJobDetail().getJobDataMap().get("cn")
         */
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("cn");
            try (PreparedStatement statement = connection.prepareStatement("insert into rabbit (created_date) values (?)")) {
                statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                statement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}