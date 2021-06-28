package io.odpf.kafkaclient.consumer;

public class NullTopicException extends RuntimeException {

    public NullTopicException(String message) {
        super(message);
    }
}
