package io.odpf.kafkaclient.consumer;

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
            bufferedWriter.write("value.deserializer org.apache.kafka.common.serialization.StringDeserializer\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCreateConsumer() {
        ConsumerFactory consumerFactory = new ConsumerFactory();

        try {
            consumerFactory.configure(propertiesFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Client client = consumerFactory.create();
        assertEquals(Consumer.class, client.getClass());
    }

    @Test
    public void shouldThrowPropertiesNotSetExceptionWhenNotConfigured() {
        ConsumerFactory consumerFactory = new ConsumerFactory();

        try {
            consumerFactory.create();
        } catch (Exception e) {
            assertEquals(PropertiesNotSetException.class, e.getClass());
        }
    }

    @After
    public void tearDown() {
        assertTrue(propertiesFile.delete());
    }
}
