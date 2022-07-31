package ru.job4j.ood.isp.menu;

public class MenuPrinter3d implements MenuPrinter {
    private static final String INDENT = "---";

    @Override
    public void print(Menu menu) {
        String string = menuToString(menu);
        System.out.println(string);
    }

    public String menuToString(Menu menu) {
        StringBuilder builder = new StringBuilder();
        for (Menu.MenuItemInfo m : menu) {
            String number = m.getNumber();
            String[] split = number.split("\\.");
            String repeat = INDENT.repeat(split.length - 1);
            builder.append(repeat).append(m.getName()).append(" ").append(number).append(System.lineSeparator());
        }
    return builder.toString();
    }
}
