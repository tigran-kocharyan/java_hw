package ru.totowka.frontend;

import ru.totowka.backend.contact.Contact;

import java.util.Map;

public final class Utils {
    private Utils() {
    }

    /**
     * Вывод в консоль коллекции контактов.
     * @param contacts
     */
    public static void printContacts(Map<Integer, Contact> contacts) {
        for (Map.Entry<Integer, Contact> contact : contacts.entrySet()) {
            System.out.println("\nContact ID: " + contact.getKey() + "\n" + contact.getValue());
        }
    }
}
