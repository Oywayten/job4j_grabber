package ru.job4j.grabber.utils;

import java.time.LocalDateTime;

/**
 * Интерфейс для парсеров даты и времени из строки в LocalDateTime
 */
public interface DateTimeParser {
    LocalDateTime parse(String parse);
}