package ru.job4j.grabber;

import java.util.List;

/**
 * Интерфейс позволяет собрать короткое описание всех объявлений,
 * а так же загрузить детали по каждому объявлению.
 */
public interface Parse {
    /**
     * этот метод загружает список объявлений по ссылке типа
     * @param link ссылка по которой происходит загрузка
     * @return возвращает список типа List<Post>
     */
    List<Post> list(String link);
}