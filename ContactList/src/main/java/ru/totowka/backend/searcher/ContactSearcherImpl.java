package ru.totowka.backend.searcher;

import ru.totowka.backend.contact.Contact;
import ru.totowka.backend.database.DataBase;

import java.util.*;
import java.util.stream.Collectors;

public class ContactSearcherImpl implements ContactSearcher {
    private final DataBase dataBase;
    private static final int RESPONSE_LIMIT = 10;

    /**
     * Публичный конструктор
     * @param dataBase
     */
    public ContactSearcherImpl(DataBase dataBase) {
        this.dataBase = Objects.requireNonNull(dataBase);
    }

    /**
     * Поиск по ФИО
     *
     * @param query ФИО
     * @return коллекцию совпадений по ФИО
     */
    @Override
    public Map<Integer, Contact> searchByFullname(String query) {
        String lowerQuery = query.toLowerCase();
        return dataBase.getContacts().entrySet().stream()
                .filter(e -> (e.getValue().getName() + " " +
                        e.getValue().getSurname() + " " +
                        e.getValue().getPatronymic()).toLowerCase().contains(lowerQuery))
                .limit(RESPONSE_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Поиск по Дню Рождения контакта
     *
     * @param query
     * @return коллекцию совпадений по Дню Рождения
     */
    @Override
    public Map<Integer, Contact> searchByBirthday(String query) {
        String lowerQuery = query.toLowerCase();
        return dataBase.getContacts().entrySet().stream()
                .filter(e -> e.getValue().getBirthday().toLowerCase().contains(lowerQuery))
                .limit(RESPONSE_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Поиск совпадений по адресу
     *
     * @param query адресс
     * @return коллекцию совпадений по адресу
     */
    @Override
    public Map<Integer, Contact> searchByAddress(String query) {
        String lowerQuery = query.toLowerCase();
        return dataBase.getContacts().entrySet().stream()
                .filter(e -> e.getValue().getAddress().toLowerCase().contains(lowerQuery))
                .limit(RESPONSE_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Поиск совпадений по номерам телефона
     *
     * @param query номер телефона
     * @return коллекцию совпадений по номерам телефона
     */
    @Override
    public Map<Integer, Contact> searchByPhoneNumber(String query) {
        String lowerQuery = query.toLowerCase();
        return dataBase.getContacts().entrySet().stream()
                .filter(e -> Arrays.stream(e.getValue().getNumbers())
                        .anyMatch(number -> number.equals(lowerQuery)))
                .limit(RESPONSE_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Поиск совпадений по электронной почте
     *
     * @param query почта
     * @return коллекция совпадений по адресу электронной почты
     */
    @Override
    public Map<Integer, Contact> searchByEmail(String query) {
        String lowerQuery = query.toLowerCase();
        return dataBase.getContacts().entrySet().stream()
                .filter(e -> e.getValue().getEmail().toLowerCase().contains(lowerQuery))
                .limit(RESPONSE_LIMIT)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Поиск дубликатов
     *
     * @param contact контакт
     * @return коллекция дубликатов данного контакта
     */
    @Override
    public List<Contact> searchDublicate(Contact contact) {
        List<Contact> contacts = new ArrayList<Contact>();

        for (String number : contact.getNumbers()) {
            contacts.addAll(searchByPhoneNumber(number).values());
        }
        contacts.addAll(searchByFullname(contact.getName() + " " +
                contact.getSurname() + " " +
                contact.getPatronymic()).values());
        contacts.addAll(searchByAddress(contact.getAddress()).values());
        contacts.addAll(searchByEmail(contact.getEmail()).values());
        contacts.addAll(searchByBirthday(contact.getBirthday()).values());

        return contacts.stream().distinct().collect(Collectors.toList());
    }
}
