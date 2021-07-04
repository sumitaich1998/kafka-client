package io.odpf.kafkaclient;

import org.aeonbits.owner.ConfigFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/**
 * The type Config parser.
 */
public class ConfigParser {

    private File readFile;
    /**
     * The Application type.
     */
    ApplicationType applicationType;

    /**
     * Instantiates a new Config parser.
     *
     * @param filePath         the file path
     * @param applicationTypes the application types
     */
    public ConfigParser(String filePath, ApplicationType applicationTypes) {
        this.readFile = new File(filePath);
        this.applicationType = applicationTypes;
    }


    /**
     * Parse core config.
     *
     * @return the core config
     */
    public CoreConfig parse() {
        HashMap<String, String> configuration = new HashMap<>();


        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(readFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String propertyText;
        try {

            while ((propertyText = bufferedReader.readLine()) != null) {
                String property = propertyText.split("=")[0];
                String value = propertyText.split("=")[1];

                configuration.put(property, value);
            }
        } catch (Exception e) {
        }
        return ConfigFactory.create(applicationType.getConfigClass(), configuration);

    }


}
