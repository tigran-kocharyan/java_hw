package ru.totowka.contact;

import java.time.LocalDate;

public class Contact {
    public Contact(String name, String surname, String patronymic, String adress,
                   String email, String mobilePhone, String landlinePhone, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = adress;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.landlinePhone = landlinePhone;
        this.birthday = birthday;
        ObjectMapper objectMapper = new ObjectMapper();
    }

    String name;
    String surname;
    String patronymic;
    String address;
    String email;
    String mobilePhone;
    String landlinePhone;
    LocalDate birthday;
}
