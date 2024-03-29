package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean wasAdded = false;
        if (findItem(childName).isPresent()) {
            return wasAdded;
        } else if (Objects.equals(Menu.ROOT, parentName)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            wasAdded = true;
        } else {
            Optional<ItemInfo> parentOp = findItem(parentName);
            if (parentOp.isPresent()) {
                List<MenuItem> children = parentOp.get().menuItem.getChildren();
                children.add(new SimpleMenuItem(childName, actionDelegate));
                wasAdded = true;
            }
        }
        return wasAdded;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(info -> new MenuItemInfo(info.menuItem, info.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            final DFSIterator iterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo next = iterator.next();
                MenuItem menuItem = next.menuItem;
                String number = next.number;
                return new MenuItemInfo(menuItem, number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        ItemInfo next = null;
        while (iterator.hasNext()) {
            next = iterator.next();
            String name1 = next.menuItem.getName();
            if (name1.equals(name)) {
                break;
            } else {
                next = null;
            }
        }
        return Optional.ofNullable(next);
    }

    /**
     * Класс реализует MenuItem для SimpleMenu
     */
    private static class SimpleMenuItem implements MenuItem {

        private final String name;
        private final List<MenuItem> children = new ArrayList<>();
        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    /**
     * Класс служит для того, чтобы скомпоновать пункт меню и номер пункта (1.1., 1.1.1. и т.д.).
     */
    private static class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

    /**
     * Это итератор, основанный на поиске в глубину.
     * Но немного модифицированный, поскольку нам удобно читать пункты меню сверху-вниз и слева-направо.
     */
    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (ListIterator<MenuItem> i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

}