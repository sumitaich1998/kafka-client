package io.odpf.kafkaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    protected Logger getLog() {
        return LOGGER;
    }

    public abstract void interact() throws IOException;
}
