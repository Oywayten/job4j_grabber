package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для парсинга даты и времени из строки в LocalDateTime.
 * Реализует метод parse из интерфейса.
 */
public class HabrCareerDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        return ZonedDateTime.parse(parse,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ"))
                .toLocalDateTime();
    }

}