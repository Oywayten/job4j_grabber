package ru.job4j.grabber;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int id;
    private final String title;
    private final String link;
    private final String description;
    private final LocalDateTime created;

    public Post(String title, String link, String description, LocalDateTime created) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = created;
    }

    public Post(int id, String title, String link, String description, LocalDateTime created) {
        this(title, link, description, created);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id).append(" ");
        sb.append(title).append("\n");
        sb.append(link).append("\n");
        sb.append(description).append("\n");
        sb.append(created).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && link.equals(post.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }
}
