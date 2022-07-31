package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private static final int NEW_ITEM = 1;
    private static final String ROOT = "Пожалуйста введите название корневого элемент";
    private static final String ITEM_NAME = "Пожалуйста введите название задачи";
    private static final String CHOOSE =
            "1 для создания нового элемента меню, иное число для выхода";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter3d printer3d = new MenuPrinter3d();
        System.out.println(CHOOSE);
        String i = sc.nextLine();
        int j = Integer.parseInt(i);
        while (j == NEW_ITEM) {
            System.out.println(ROOT);
            String parentName = sc.nextLine();
            System.out.println(ITEM_NAME);
            String childName = sc.nextLine();
            menu.add(parentName, childName, STUB_ACTION);
            printer3d.print(menu);
            System.out.println(CHOOSE);
            i = sc.nextLine();
            j = Integer.parseInt(i);
        }
    }
}
