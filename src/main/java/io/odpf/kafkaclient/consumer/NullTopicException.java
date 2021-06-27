package io.odpf.kafkaclient.consumer;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class NullTopicException extends RuntimeException {

    @Override
    public void printStackTrace() {
        Logger log = Logger.getLogger("client.logger");
        log.setLevel(Level.ALL);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        log.addHandler(handler);
        log.info("Topic cannot be null");
    }
}
