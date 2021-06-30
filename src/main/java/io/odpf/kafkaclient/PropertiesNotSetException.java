package io.odpf.kafkaclient;

public class PropertiesNotSetException extends RuntimeException {

    public PropertiesNotSetException(String message) {
        super(message);
    }
}
