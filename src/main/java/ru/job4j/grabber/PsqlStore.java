package ru.job4j.grabber;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    /**
     * Конструктор получает на входа Properties, парсит их, загружает и создает подключение Connection
     * @param cfg параметры типа Properties
     */
    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод main для простой проверки работоспособности
     * @param args список параметров - не задается в данном случае
     */
    public static void main(String[] args) {
        Properties cfg = new Properties();
        try (InputStream stream = PsqlStore.class.getClassLoader().getResourceAsStream("post.properties")) {
            cfg.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PsqlStore store = new PsqlStore(cfg)) {
            store.save(new Post("Java Developer", "https://job4j.ru/profile/exercise/56/task-view/363",
                    "Очень крутая вакансия", LocalDateTime.now()));
            System.out.println(store.findById(1));
            store.getAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для сохранения объявления типа Post в базе объявлений о вакансиях
     * @param post объявление
     */
    @Override
    public void save(Post post) {
        try (PreparedStatement statement = cnn.prepareStatement(
                "insert into post (name, text, link, created) values(?, ?, ?, ?)")) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод получает все записи из базы
     * @return возвращает список вакансий типа List<Post>
     */
    @Override
    public List<Post> getAll() {
        List<Post> posts = new LinkedList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from post")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    posts.add(getPost(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    /**
     * Приватный вспомогательный метод для получения Post из ResultSet
     * @param set результат выборки из базы типа ResultSet
     * @return объявление о вакансии типа Post
     * @throws SQLException ошибка SQL
     */
    private Post getPost(ResultSet set) throws SQLException {
        return new Post(
                set.getInt("id"),
                set.getString("name"),
                set.getString("link"),
                set.getString("text"),
                set.getTimestamp("created").toLocalDateTime()
        );
    }

    /**
     * Поиск объявления в базе по id
     * @param id id для поиска
     * @return объявление из базы типа Post
     */
    @Override
    public Post findById(int id) {
        Post post = null;
        try (PreparedStatement statement = cnn.prepareStatement("select * from post where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    post = getPost(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}