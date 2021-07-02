package io.odpf.kafkaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Application {

 Logger LOGGER = LoggerFactory.getLogger(Application.class);

    void execute();
}
