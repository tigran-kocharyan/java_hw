package ru.totowka;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * Класс для отправки запроса к freegeoip.app
 */
public class Requester {
    /**
     * Метод отравки запроса и получения строки JSON.
     * @param url ссылка для обращения.
     * @return JSON ответ.
     * @throws IOException
     */
    public String getByRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Построение ответа от API.
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Автоматическое закрытие потока внутри try.
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }
}
