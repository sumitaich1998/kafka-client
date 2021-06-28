package io.odpf.kafkaclient.producer;

import io.odpf.kafkaclient.Client;
import io.odpf.kafkaclient.PropertiesNotSetException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProducerFactoryTest {

    File propertiesFile;

    @Before
    public void setUp() {
        propertiesFile = new File("producer_temp.properties");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(propertiesFile));

            bufferedWriter.write("producer\n");
            bufferedWriter.write("bootstrap.servers=localhost:9092\n");
            bufferedWriter.write("acks=all\n");
            bufferedWriter.write("retries=0\n");
            bufferedWriter.write("batch.size=16384\n");
            bufferedWriter.write("linger.ms=1\n");
            bufferedWriter.write("buffer.memory=33554432\n");
            bufferedWriter.write("key.serializer=org.apache.kafka.common.serialization.StringSerializer\n");
            bufferedWriter.write("value.serializer=org.apache.kafka.common.serialization.StringSerializer\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCreateProducer() {
        ProducerFactory producerFactory = new ProducerFactory();

        try {
            producerFactory.configure(propertiesFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Client client = producerFactory.create();
        assertEquals(Producer.class, client.getClass());
    }

    @Test
    public void shouldThrowPropertiesNotSetExceptionWhenNotConfigured() {
        ProducerFactory producerFactory = new ProducerFactory();

        try {
            producerFactory.create();
        } catch (Exception e) {
            assertEquals(PropertiesNotSetException.class, e.getClass());
        }
    }

    @After
    public void tearDown() {
        assertTrue(propertiesFile.delete());
    }
}
