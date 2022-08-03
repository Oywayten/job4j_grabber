package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final int NEW_USERS = 1000;
    private final List<User> users = new ArrayList<>();
    private final Random random;
    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            users.add(new User(
                    new StringBuilder()
                            .append(surnames.get(random.nextInt(surnames.size())))
                            .append(SEPARATOR)
                            .append(names.get(random.nextInt(names.size())))
                            .append(SEPARATOR)
                            .append(patrons.get(random.nextInt(patrons.size())))
                            .toString()));
        }
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}