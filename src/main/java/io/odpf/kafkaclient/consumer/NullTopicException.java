package io.odpf.kafkaclient.consumer;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class NullTopicException extends RuntimeException {

    public NullTopicException(String message) {
        super(message);
    }
}
