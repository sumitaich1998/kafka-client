package io.odpf.kafkaclient.producer;

public class InvalidNumberOfArgumentsException extends RuntimeException {

    public InvalidNumberOfArgumentsException(String message) {
        super(message);
    }
}
