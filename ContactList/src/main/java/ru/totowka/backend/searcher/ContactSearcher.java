package ru.totowka.backend.searcher;

import ru.totowka.backend.contact.Contact;

import java.util.List;
import java.util.Map;

public interface ContactSearcher {
    public Map<Integer, Contact> searchByFullname(String query);

    public Map<Integer, Contact> searchByBirthday(String query);

    public Map<Integer, Contact> searchByAddress(String query);

    public Map<Integer, Contact> searchByPhoneNumber(String query);

    public Map<Integer, Contact> searchByEmail(String query);

    public List<Contact> searchDublicate(Contact contact);
}
