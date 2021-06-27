package io.odpf.kafkaclient.consumer;

import org.junit.Before;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ConsumerFactoryTest {

    File propertiesFile;

    @Before
    public void setUp() {

        propertiesFile = new File("consumer_temp.properties");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(propertiesFile));

            bufferedWriter.write("bootstrap.servers localhost:9092\n");
            bufferedWriter.write("group.id test\n");
            bufferedWriter.write("enable.auto.commit true\n");
            bufferedWriter.write("auto.commit.interval.ms 1000\n");
            bufferedWriter.write("session.timeout.ms 30000\n");
            bufferedWriter.write("key.deserializer org.apache.kafka.common.serialization.StringDeserializer\n");
            bufferedWriter.write("value.deserializer org.apache.kafka.common.serialization.StringDeserializer");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
