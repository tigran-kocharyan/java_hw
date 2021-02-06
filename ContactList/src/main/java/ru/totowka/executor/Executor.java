package ru.totowka.executor;

import ru.totowka.backend.database.DataBase;
import ru.totowka.backend.database.DataBaseImpl;
import ru.totowka.frontend.Interface;

public class Executor {
    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        DataBase db = new DataBaseImpl("target\\contacts.json");
        Interface inf = new Interface(db);

        inf.execute();
    }
}
