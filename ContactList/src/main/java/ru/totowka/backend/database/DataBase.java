package ru.totowka.backend.database;

import ru.totowka.backend.contact.Contact;
import java.io.IOException;
import java.util.Map;

public interface DataBase {
    public boolean add(Contact contact);
    public Map<Integer, Contact> getContacts();
    public boolean remove(int id);
    public boolean has(Contact contact);
}
