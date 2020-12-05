package ru.totowka;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileIteratorTest {
    static final String TEXT_DOUBLE_LINE = "I\nLove\nJava\n\nAnd\nskciD\n";
    static final String TEXT_EMPTY = "";
    static final String TEXT_ONE_LINE = "Ku4uk1Love\n";
    static final String TEXT_EXAMPLE = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n";

    FileIterator fileIterator;

    /**
     * Создание файла.
     * @param text
     */
    void createFile(String text) {
        try {
            FileWriter writer = new FileWriter(new File("test.txt"));
            writer.write(text);
            writer.close();
            fileIterator = new FileIterator("test.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Проверка на корректность чтения текстк с двумя отступами.
     */
    @Test
    void Check_DoubleNewLineFile() {
        createFile(TEXT_DOUBLE_LINE);
        StringBuilder result = new StringBuilder();
        while (fileIterator.hasNext()) {
            result.append(fileIterator.next() + "\n");
        }
        assertEquals(TEXT_DOUBLE_LINE, result.toString());
    }

    /**
     * Проверка на чтение пустого файла.
     */
    @Test
    void Check_EmptyFile() {
        createFile(TEXT_EMPTY);
        StringBuilder result = new StringBuilder();
        while (fileIterator.hasNext()) {
            result.append(fileIterator.next() + "\n");
        }
        assertEquals(TEXT_EMPTY, result.toString());
    }

    /**
     * Проверка на корректность считывания файла с одной строкой данных.
     * Проверка на корректную остановку.
     */
    @Test
    void Check_OneLineFile() {
        createFile(TEXT_ONE_LINE);
        StringBuilder result = new StringBuilder();
        while (fileIterator.hasNext()) {
            result.append(fileIterator.next() + "\n");
        }
        assertEquals(TEXT_ONE_LINE, result.toString());
    }

    /**
     * Пример построчного вывода.
     */
    @Test
    void Example_Output() {
        createFile(TEXT_EXAMPLE);
        while (fileIterator.hasNext()) {
            System.out.println(fileIterator.next());
        }
    }
}