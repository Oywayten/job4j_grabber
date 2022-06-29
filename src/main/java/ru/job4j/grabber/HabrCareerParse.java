package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer", SOURCE_LINK);

    private static String retrieveDescription(String link) throws IOException {
        Connection linkConnection = Jsoup.connect(link);
        Document linkDocument;
        linkDocument = linkConnection.get();
        Element descriprion = linkDocument.selectFirst(".style-ugc");
        return descriprion.text();
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 5; i++) {
            final String PAGE_NUMBER_LINK = String.format("%s?page=%d", PAGE_LINK, i);
            Connection connection = Jsoup.connect(PAGE_NUMBER_LINK);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
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

                String vacancyName = titleElement.text();
                System.out.printf("%s %s %s%n%s%n%n", vacancyName, link, datetime1.attr("datetime"), description);

            });
        }
    }
}