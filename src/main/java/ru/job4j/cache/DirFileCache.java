package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Класс эмулирует поведение AbstractCache.
 * Считывает текстовый файл и выдает текст при запросе имени файла.
 * Если в кэше файла нет, то кэш загружает себе данные.
 * По умолчанию в кэше нет ни одного файла, а текстовые файлы лежат в одной директории.
 * При запросе по ключу Names.txt - кеш возвращает содержимое файла Names.txt
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String s = "";
        File file = new File(cachingDir, key);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            s = br.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}