package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private static final int NEW_ITEM = 1;
    private static final int SHOW = 2;
    private static final int EXIT = 3;
    private static final String ROOT = """
            Пожалуйста введите название корневого элемент или нажмите Enter,
            если вводите корневой элемент
            """;
    private static final String ITEM_NAME = "Введите название задачи";
    private static final String CHOOSE = """
            Выберите
            1 чтобы добавить пункт меню
            2 чтобы вывести меню
            3 чтобы выйти
            """;
    private static final String GOODBYE = "Всего доброго!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter3d printer3d = new MenuPrinter3d();
        System.out.println(CHOOSE);
        int j = Integer.parseInt(sc.nextLine());
        while (j != EXIT) {
            if (j == SHOW) {
                printer3d.print(menu);
            } else if (j == NEW_ITEM) {
                System.out.println(ROOT);
                String parentName = sc.nextLine();
                if (("").equals(parentName)) {
                    parentName = Menu.ROOT;
                }
                System.out.println(ITEM_NAME);
                String childName = sc.nextLine();
                menu.add(parentName, childName, STUB_ACTION);
            }
            System.out.println(CHOOSE);
            j = Integer.parseInt(sc.nextLine());
        }
        System.out.println(GOODBYE);
    }
}
