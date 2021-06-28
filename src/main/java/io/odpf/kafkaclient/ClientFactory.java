package io.odpf.kafkaclient;

import io.odpf.kafkaclient.consumer.ConsumerFactory;
import io.odpf.kafkaclient.producer.ProducerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class ClientFactory {
    private Properties properties;

    public final void configure(File file) throws IOException {
        properties = new Properties();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String propertyText = "";

        if (this.getClass().equals(ProducerFactory.class)) {
            while (!(propertyText = bufferedReader.readLine()).equals("producer")) ;
            while ((propertyText = bufferedReader.readLine()) != null &&
                    !propertyText.equals("consumer")) {
                if (propertyText.equals("producer")) continue;
                String property = propertyText.split("=")[0];
                String value = propertyText.split("=")[1];

                properties.put(property, value);
            }
        }

        if (this.getClass().equals(ConsumerFactory.class)) {
            while (!(propertyText = bufferedReader.readLine()).equals("consumer")) ;
            while ((propertyText = bufferedReader.readLine()) != null &&
                    !propertyText.equals("producer")) {
                String property = propertyText.split("=")[0];
                String value = propertyText.split("=")[1];

                properties.put(property, value);
            }
        }
    }

    protected final Properties getProperties() {
        return properties;
    }

    public abstract Client create();

}
