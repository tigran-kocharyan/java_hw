package ru.totowka.backend.contact;

import java.util.Objects;
import java.util.Arrays;

public class Contact {
    String name;
    String surname;
    String patronymic;
    String address;
    String email;
    String[] numbers;
    String birthday;

    /**
     * Класс для определения контакта человека.
     */
    public Contact() {}

    public Contact(String name, String surname, String patronymic, String address,
                   String email, String[] numbers, String birthday) {
        this.name = Objects.requireNonNull(name, "Name should be declared.");
        this.surname = Objects.requireNonNull(surname, "Surname should be declared.");
        this.patronymic = Objects.requireNonNull(patronymic, "Patronymic should be declared.");
        this.address = Objects.requireNonNull(address, "Address should be declared.");
        this.email = Objects.requireNonNull(email, "Email should be declared.");
        this.numbers = Objects.requireNonNull(numbers, "Numbers should be declared.");
        this.birthday = Objects.requireNonNull(birthday, "Birthday should be declared.");
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String[] getNumbers() {
        return numbers;
    }
    public String getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) &&
                surname.equals(contact.surname) &&
                patronymic.equals(contact.patronymic) &&
                address.equals(contact.address) &&
                email.equals(contact.email) &&
                Arrays.equals(numbers, contact.numbers) &&
                birthday.equals(contact.birthday);
    }

    @Override
    public String toString() {
        return "Fullname: \""+ surname + " " + name + " " + patronymic + '\"'+
                ",\nPhone Numbers: " + Arrays.toString(numbers) +
                ",\nEmail: " + email +
                ",\nBirthday: " + birthday +
                ",\nAddress: " + address + ".";
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, patronymic, address, email, birthday);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }
}
