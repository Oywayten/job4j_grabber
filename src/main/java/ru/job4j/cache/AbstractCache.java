package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный кэш
 *
 * @param <K> ключ для карты
 * @param <V> значение для карты
 */
public abstract class AbstractCache<K, V> {
    /**
     * Мапа для хранения ключа и значение (по софт-ссылке)
     */
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод добавляет пару ключ=значение в мапу
     *
     * @param key   ключ
     * @param value значение
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Метод возвращает значение по запрошенному ключу
     *
     * @param key ключ запроса
     * @return значение
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, cache.put(key, new SoftReference<>(load(key)))).get();
        return value;
    }

    /**
     * Читает значение из источника подкласса, выводит его пользователю и допавляет в мапу
     *
     * @param key ключ поиска значения в источнике
     * @return значение
     */
    protected abstract V load(K key);

}