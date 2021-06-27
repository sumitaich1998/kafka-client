package io.odpf.kafkaclient.console;

import io.odpf.kafkaclient.Client;
import io.odpf.kafkaclient.ClientFactory;
import io.odpf.kafkaclient.consumer.ConsumerFactory;
import io.odpf.kafkaclient.producer.ProducerFactory;

import java.io.File;
import java.io.IOException;

public class Console {

    public static void main(String... args) throws IOException {
        File propertiesFile = new File(args[1]);
        ClientFactory clientFactory = (args[0].equals("producer")) ?
                new ProducerFactory() : new ConsumerFactory();

        clientFactory.configure(propertiesFile);
        Client client = clientFactory.create();

        client.interact();
    }
}
