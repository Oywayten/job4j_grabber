package ru.job4j.cache;

import java.util.Scanner;

/**
 * Класс для работы с пользователем.
 * Позволяет указать кэшируемую директорию, загрузить содержимое файла в кэш и получить содержимое файла из кэша
 */
public class Emulator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter directory: ");
        DirFileCache cache = new DirFileCache(sc.nextLine());
        int i = 1;
        while (i == 1) {
            System.out.println("Please enter file for look: ");
            System.out.println(cache.get(sc.nextLine()));
            System.out.println("Choose 1 for look next file");
            i = sc.nextInt();
            sc.nextLine();
        }
        System.out.println("Goodbye!");
    }
}
