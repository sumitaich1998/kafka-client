package io.odpf.kafkaclient;

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
        while ((propertyText = bufferedReader.readLine()) != null) {
            String property = propertyText.split(" ")[0];
            String value = propertyText.split(" ")[1];

            properties.put(property, value);
        }
    }

    protected final Properties getProperties() {
        return properties;
    }
}
