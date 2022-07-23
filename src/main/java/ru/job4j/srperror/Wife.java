package ru.job4j.srperror;

public class Wife {
    private int age;
    private final String name;
    private Husband husband;
    private boolean atHome = true;

    public Wife(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setHusband(Husband husband) {
        if (husband == null) {
            this.husband = husband;
        }
    }

    public void goToWork() {
        if (atHome) {
            atHome = false;
        }
    }

    class Husband {
        private int age;
        private final String name;

        public Husband(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
