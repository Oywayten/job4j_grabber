package ru.job4j.cache;

import java.util.Scanner;

/**
 * Класс для работы с пользователем.
 * Позволяет указать кэшируемую директорию, загрузить содержимое файла в кэш и получить содержимое файла из кэша
 */
public class Emulator {
    public static final int GO = 1;
    public static final String CHOOSE_DIRECTORY = "Please enter directory: ";
    public static final String CHOOSE_FILE = "Please enter file for look: ";
    public static final String CHOOSE_ONE = "Choose 1 for look next file: ";
    public static final String GOODBYE = "Goodbye!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(CHOOSE_DIRECTORY);
        DirFileCache cache = new DirFileCache(sc.nextLine());
        int i = GO;
        while (i == GO) {
            System.out.println(CHOOSE_FILE);
            System.out.println(cache.get(sc.nextLine()));
            System.out.println(CHOOSE_ONE);
            i = sc.nextInt();
            sc.nextLine();
        }
        System.out.println(GOODBYE);
    }
}
