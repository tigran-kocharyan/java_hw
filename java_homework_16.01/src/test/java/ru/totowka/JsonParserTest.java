package ru.totowka;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    private static String input;
    private static JsonParser parserJson;

    @BeforeAll
    public static void setup() throws IOException {
        Requester requester = new Requester();
        input = requester.getByRequest("https://freegeoip.app/json/");
        parserJson = new JsonParser();
    }

    @Test
    public void getShortLocation_PassCorrectJson_ReturnsShortLocation() {
        String expectedResult = "Uzbekistan\nTashkent\n";
        assertEquals(expectedResult, parserJson.getShortLocation(input));
    }

    @Test
    public void getShortLocation_PassCorrectJson_ReturnsLongLocation() {
        String expectedResult = "Uzbekistan\nToshkent Shahri\nTashkent\n41.3171\n69.2494\n";
        assertEquals(expectedResult, parserJson.getLongLocation(input));
    }

    @Test
    public void getShortLocation_PassNullJson_ReturnsNullLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            parserJson.getShortLocation(null);
        });
    }

    @Test
    public void getLongLocation_PassNullJson_ReturnsNullLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            parserJson.getLongLocation(null);
        });
    }
}