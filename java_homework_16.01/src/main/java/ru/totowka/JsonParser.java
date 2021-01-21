package ru.totowka;

import org.json.JSONObject;

/**
 * Класс для реализации парсинга JSON и извлечения из него локации.
 */
public class JsonParser {
    /**
     * Парсит JSON-строку и извлекает локацию в коротком виде.
     * @param json json строка в виде JSON.
     * @return короткий адрес.
     */
    public String getShortLocation(String json) {
        if(json == null) {
            return "Location: Null";
        }
        JSONObject obj = new JSONObject(json);
        return obj.getString("country_name") + "\n" +
                obj.getString("city") + "\n";
    }


    /**
     * Парсит JSON-строку и извлекает локацию в полном виде.
     * @param json строка в виде JSON.
     * @return полный адрес.
     */
    public String getLongLocation(String json) {
        if(json == null) {
            return "Location: Null";
        }
        JSONObject obj = new JSONObject(json);
        return obj.getString("country_name") + "\n" +
                obj.getString("region_name") + "\n" +
                obj.getString("city") + "\n" +
                obj.getDouble("latitude") + "\n" +
                obj.getDouble("longitude") + "\n";
    }
}
