package ru.job4j.ood.isp.menu;

import java.util.List;

/**
 * Интерфейс пункта меню
 */
public interface MenuItem {

    String getName();

    List<MenuItem> getChildren();

    ActionDelegate getActionDelegate();

}