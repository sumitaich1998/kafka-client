package io.odpf.kafkaclient;

import org.aeonbits.owner.ConfigFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class ConfigParser {

    private File readFile;
    ApplicationType applicationType;

    public ConfigParser(String filePath, ApplicationType applicationTypes) {
        this.readFile = new File(filePath);
        this.applicationType = applicationTypes;
    }


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
