package ru.job4j.grabber;

import java.util.List;

/**
 * Интерфейс для связи с базой
 */
public interface Store {

    /**
     * Cохраняет объявление в базе
     * @param post объявление
     */
    void save(Post post);

    /**
     * Позволяет извлечь объявления из базы
     * @return список типаList<Post>
     */
    List<Post> getAll();

    /**
     * Метод позволяет извлечь объявление из базы по id
     * @param id id для поиска
     * @return возвращает объявление типа Post
     */
    Post findById(int id);
}
