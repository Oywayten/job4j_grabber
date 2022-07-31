package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    static Menu menu = new SimpleMenu();

    @BeforeAll
    static void setup() {
        menu.add(Menu.ROOT, "Сходить в магазин", TODOApp.STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", TODOApp.STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", TODOApp.STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", TODOApp.STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", TODOApp.STUB_ACTION);
    }

    @Test
    public void whenAddThenReturnSame() {
        assertThat(new Menu.MenuItemInfo(
                "Сходить в магазин", List.of("Купить продукты"), TODOApp.STUB_ACTION, "1."
        )).isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
        )).isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."
        )).isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenPrintWithIndents() {
        MenuPrinter3d printer3d = new MenuPrinter3d();
        String expected = """
                Сходить в магазин 1.
                ---Купить продукты 1.1.
                ------Купить хлеб 1.1.1.
                ------Купить молоко 1.1.2.
                Покормить собаку 2.
                """;
        String actual = printer3d.menuToString(menu);
        assertThat(actual).isEqualToNormalizingNewlines(expected);
    }

}