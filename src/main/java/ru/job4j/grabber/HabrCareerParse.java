package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс парсит хабр на тему вакансий по java
 */
public class HabrCareerParse implements Parse {

    /**
     * Число страниц для парсинга
     */
    public static final int VOL = 5;
    /**
     * Ссылка на портал с вакансиями
     */
    private static final String SOURCE_LINK = "https://career.habr.com";
    /**
     * Ссыдка на раздел с вакансиями
     */
    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);
    /**
     * Парсер даты и времени с сайта в формат LocalDateTime
     */
    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser parser) {
        dateTimeParser = parser;
    }

    public static void main(String[] args) {
        HabrCareerParse careerParse = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> list = new ArrayList<>();
        for (int i = 1; i <= VOL; i++) {
            final String PAGE_NUMBER_LINK = String.format("%s?page=%d", PAGE_LINK, i);
            list.addAll(careerParse.list(PAGE_NUMBER_LINK));
        }
        System.out.println("Найдено " + list.size() + " вакансий");
        list.forEach(System.out::println);
    }

    /**
     * Метод возвращает описание вакансии
     *
     * @param link ссылка на вакансию
     * @return строковое представление описания
     * @throws IOException ошибка ввода вывода
     */
    private String retrieveDescription(String link) throws IOException {
        Connection linkConnection = Jsoup.connect(link);
        Document linkDocument;
        linkDocument = linkConnection.get();
        Element descriprion = linkDocument.selectFirst(".style-ugc");
        return descriprion.text();
    }

    /**
     * Метод загружает список всех постов
     *
     * @param link ссылка раздела с вакансиями
     * @return список всех постов с вакансиями
     */
    @Override
    public List<Post> list(String link) {
        List<Post> arrList = new ArrayList<>();
        Connection connection = Jsoup.connect(link);
        Document document;
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        Elements rows = document.select(".vacancy-card__inner");
        rows.forEach(row -> arrList.add(postsPars(row)));
        return arrList;
    }

    private Post postsPars(Element row) {
        Element titleElement = row.select(".vacancy-card__title").first();
        Element datetime1 = row.selectFirst(".basic-date");
        Element linkElement = titleElement.child(0);
        String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        String description = "";
        try {
            description = retrieveDescription(link);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Post(titleElement.text(), link, description, dateTimeParser.parse(datetime1.attr("datetime")));
    }
}
