package io.odpf.kafkaclient.consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConsumerTest {
    File propertiesFile;
    Consumer consumer;

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
        ConsumerFactory consumerFactory = new ConsumerFactory();

        try {
            consumerFactory.configure(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer = consumerFactory.create();
    }

    @Test
    public void shouldThrowNullTopicExceptionWhenNullTopicPassed() {
        try {
            consumer.readEvent(null);
        } catch (Exception e) {
            assertEquals(NullTopicException.class, e.getClass());
        }
    }

    @Test
    public void shouldNotReturnNullList() {
        String topic = "instagram-notifications";
        ArrayList<String> eventData = consumer.readEvent(topic);

        assertNotNull(eventData);
    }

    @After
    public void tearDown() {
        assertTrue(propertiesFile.delete());
    }
}
