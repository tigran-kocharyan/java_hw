package ru.totowka.backend.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.totowka.backend.contact.Contact;
import ru.totowka.backend.exceptions.SerializeException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonSerializerImpl implements JsonSerializer {
    String SERIALIZE_ERROR_MESSAGE = "Error while serializing: ";
    String DESERIALIZE_ERROR_MESSAGE = "Error while deserializing: ";
    String FILE_ERROR_MESSAGE = "Error while creating file: ";

    ObjectMapper objectMapper;
    String path;
    File file;

    /**
     * Публичный конструктор
     * @param path
     */
    public JsonSerializerImpl(String path) {
        this.objectMapper = new ObjectMapper();
        this.path = path;
        this.file = new File(path);
        try {
            this.file.createNewFile();
        } catch (IOException exception) {
            throw new SerializeException(FILE_ERROR_MESSAGE, exception);
        }
    }

    /**
     * Метод сериализации Map<Integer, Contact> в JSON формат и запись в файл.
     *
     * @param contacts
     * @throws SerializeException в случае отсутствия/ошибки в файле.
     */
    @Override
    public void serialize(Map<Integer, Contact> contacts) {
        try {
            objectMapper.writeValue(file, contacts);
        } catch (IOException exception) {
            throw new SerializeException(SERIALIZE_ERROR_MESSAGE, exception);
        }
    }

    /**
     * Метод дусериализации JSON из файла в Map<Integer, Contact>.
     *
     * @throws SerializeException в случае отсутствия/ошибки в файле.
     **/
    @Override
    public HashMap<Integer, Contact> deserialize() {
        try {
            if(file.length() == 0) {
                return new HashMap<Integer, Contact>();
            }
            return objectMapper.readValue(file, new TypeReference<HashMap<Integer, Contact>>() {
            });
        } catch (IOException exception) {
            throw new SerializeException(DESERIALIZE_ERROR_MESSAGE, exception);
        }
    }
}
