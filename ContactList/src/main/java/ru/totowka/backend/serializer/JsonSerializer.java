package ru.totowka.backend.serializer;

import ru.totowka.backend.contact.Contact;
import ru.totowka.backend.exceptions.SerializeException;

import java.util.HashMap;
import java.util.Map;

public interface JsonSerializer {
    /**
     * Метод сериализации Map<Integer, Contact> в JSON формат и запись в файл.
     *
     * @param contacts
     * @throws SerializeException в случае отсутствия/ошибки в файле.
     */
    public void serialize(Map<Integer, Contact> contacts);

    /**
     * Метод дусериализации JSON из файла в Map<Integer, Contact>.
     *
     * @throws SerializeException в случае отсутствия/ошибки в файле.
     **/
    public HashMap<Integer, Contact> deserialize();
}
