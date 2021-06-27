package io.odpf.kafkaclient.producer;

import org.junit.Before;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ProducerFactoryTest {

    File propertiesFile;

    @Before
    public void setUp() {

        propertiesFile = new File("producer_temp.properties");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(propertiesFile));

            bufferedWriter.write("bootstrap.servers localhost:9092\n");
            bufferedWriter.write("acks all\n");
            bufferedWriter.write("retries 0\n");
            bufferedWriter.write("batch.size 16384\n");
            bufferedWriter.write("linger.ms 1\n");
            bufferedWriter.write("buffer.memory 33554432\n");
            bufferedWriter.write("key.serializer org.apache.kafka.common.serialization.StringSerializer\n");
            bufferedWriter.write("value.serializer org.apache.kafka.common.serialization.StringSerializer");

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
