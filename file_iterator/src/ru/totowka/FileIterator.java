package ru.totowka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * Класс для считывания текста из файла.
 */
public class FileIterator implements AutoCloseable, Iterator<String> {
    private final BufferedReader input;
    private String nextLine;

    /**
     * Создание объекта.
     * @param path
     * @throws FileNotFoundException
     */
    public FileIterator(String path) throws FileNotFoundException {
        input = new BufferedReader(new FileReader(path));
        nextLine = null;
    }


    /**
     * Закрытие потока.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        input.close();
    }

    /**
     * Проверка на наличие следующей строки.
     * @return true - если есть следующая строка, false otherwise.
     */
    @Override
    public boolean hasNext() {
        try {
            return input.ready();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Считывание строк.
     * @return следуюущю строку в файлу.
     */
    @Override
    public String next() {
        if(hasNext()) {
            try {
                return input.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } return null;
    }
}
