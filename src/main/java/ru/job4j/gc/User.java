package ru.job4j.gc;

public class User {

    private int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void main(String[] args) {
        User u1 = new User(20, "Ivan1");
        for (int i = 0; i < 1000; i++) {
            new User(i, "N" + i);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}