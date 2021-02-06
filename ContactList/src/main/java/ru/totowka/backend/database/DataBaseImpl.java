package ru.totowka.backend.database;

import ru.totowka.backend.contact.Contact;
import ru.totowka.backend.searcher.ContactSearcher;
import ru.totowka.backend.searcher.ContactSearcherImpl;
import ru.totowka.backend.serializer.JsonSerializer;
import ru.totowka.backend.serializer.JsonSerializerImpl;

import java.util.*;

public class DataBaseImpl implements DataBase {
    JsonSerializer jsonSerializer;
    ContactSearcher contactSearcher;
    int counter;

    /**
     * Конструктор класса. Устанавливает поля и текущее значение счетчика ID.
     * @param path путь к файлу
     */
    public DataBaseImpl(String path) {
        jsonSerializer = new JsonSerializerImpl(path);
        contactSearcher = new ContactSearcherImpl(this);

        Map<Integer, Contact> contacts = jsonSerializer.deserialize();
        if(contacts.entrySet().size()!=0) {
            counter = contacts.keySet().stream().max(Integer::compare).get() + 1;
        } else {
            counter = 1;
        }
    }

    /**
     * Добавление контакта в книжку
     * @param contact контакт
     * @return true - если добавлено, false в противном случае
     */
    @Override
    public boolean add(Contact contact) {
        Map<Integer, Contact> contacts = jsonSerializer.deserialize();

        if(contactSearcher.searchDublicate(contact).size() != 0) {
            return false;
        } else {
            contacts.put(counter, contact);
            counter++;
            jsonSerializer.serialize(contacts);
            return true;
        }
    }

    /**
     *
     * @return все контакты в книжке
     */
    @Override
    public Map<Integer, Contact> getContacts() {
        return Collections.unmodifiableMap
                (new HashMap<Integer, Contact>(jsonSerializer.deserialize()));
    }

    /**
     * Удаляет контакт с данным ID
     * @param id id контакта
     * @return true - если удалено, false в противном случае
     */
    @Override
    public boolean remove(int id) {
        HashMap<Integer, Contact> contacts = jsonSerializer.deserialize();
        Contact result = contacts.remove(id);
        jsonSerializer.serialize(contacts);

        return result != null;
    }

    /**
     * наличие контакта в книжке.
     * @param contact контакт
     * @return true - если есть, false в противном случае
     */
    @Override
    public boolean has(Contact contact) {
        return jsonSerializer.deserialize().containsValue(contact);
    }
}
