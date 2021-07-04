package io.odpf.kafkaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The interface Application.
 */
public interface Application {

    /**
     * The constant LOGGER.
     */
    Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * Execute.
     */
    void execute();
}
