package ru.totowka;

import java.io.IOException;

/**
 * Класс для запуска запроса и парсинга.
 */
public class OutputLocation {
    public static void main(String[] args) {
        // Отправляем запрос.
        String result = "";
        Requester requester = new Requester();
        try {
            result = requester.getByRequest("https://freegeoip.app/json/");
        } catch (IOException | NullPointerException ex) {
            System.out.println("Invalid URL.");
        }

        // Парсим строку.
        JsonParser parser = new JsonParser();
        String location = parser.getLongLocation(result);

        // Выводим в консоль данные.
        System.out.println(location);
    }
}
