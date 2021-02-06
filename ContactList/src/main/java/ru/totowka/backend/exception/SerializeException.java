package ru.totowka.backend.exceptions;

public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(String message, Throwable exception) {
        super(message, exception);
    }
}
