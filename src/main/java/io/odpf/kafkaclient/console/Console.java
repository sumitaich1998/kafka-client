package io.odpf.kafkaclient.console;

import io.odpf.kafkaclient.Client;
import io.odpf.kafkaclient.ClientFactory;
import io.odpf.kafkaclient.consumer.ConsumerFactory;
import io.odpf.kafkaclient.producer.ProducerFactory;

import java.io.File;
import java.io.IOException;

public class Console {

    public static void main(String... args) throws IOException {
        if (args.length != 2)
            throw new IllegalArgumentException("Invalid number of arguments");
        if (!(args[0].equals("producer") || args[0].equals("consumer")))
            throw new IllegalArgumentException("Argument must be \"producer\" or \"consumer\"");
        if (!args[1].endsWith(".properties"))
            throw new IllegalArgumentException("Invalid properties file");

        File propertiesFile = new File(args[1]);
        ClientFactory clientFactory = (args[0].equals("producer")) ?
                new ProducerFactory() : new ConsumerFactory();

        clientFactory.configure(propertiesFile);
        Client client = clientFactory.create();

        client.interact();
    }
}
