package io.odpf.kafkaclient;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PropertiesNotSetException extends RuntimeException {

    public PropertiesNotSetException(String message) {
        super(message);
    }
}
