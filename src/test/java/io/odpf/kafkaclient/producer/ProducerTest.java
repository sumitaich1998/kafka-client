package io.odpf.kafkaclient.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class ProducerTest {

    File propertiesFile;
    Producer producer;

    @Before
    public void setUp() {
        propertiesFile = new File("producer_temp.properties");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(propertiesFile));

            bufferedWriter.write("producer\n");
            bufferedWriter.write("bootstrap.servers localhost:9092\n");
            bufferedWriter.write("acks all\n");
            bufferedWriter.write("retries 0\n");
            bufferedWriter.write("batch.size 16384\n");
            bufferedWriter.write("linger.ms 1\n");
            bufferedWriter.write("buffer.memory 33554432\n");
            bufferedWriter.write("key.serializer org.apache.kafka.common.serialization.StringSerializer\n");
            bufferedWriter.write("value.serializer org.apache.kafka.common.serialization.StringSerializer\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProducerFactory producerFactory = new ProducerFactory();

        try {
            producerFactory.configure(propertiesFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer = producerFactory.create();
    }

    @Test
    public void shouldWriteEventWithoutKeyInput() {
        String topic = "instagram_notifications";
        String value = "new_post_like";
        Future<RecordMetadata> future = producer.writeEvent(topic, value);

        assertFalse(future.isCancelled());
    }

    @Test
    public void shouldWriteEventWithKeyInput() {
        String topic = "instagram_notifications";
        String key = "a";
        String value = "new_post_like";
        Future<RecordMetadata> future = producer.writeEvent(topic, key, value);

        assertFalse(future.isCancelled());
    }

    @Test
    public void shouldThrowInvalidNumberOfArgumentsExceptionWhenSingleArgument() {
        try {
            producer.writeEvent("instagram_notifications");
        } catch (Exception e) {
            assertEquals(InvalidNumberOfArgumentsException.class, e.getClass());
        }
    }

    @After
    public void tearDown() {
        assertTrue(propertiesFile.delete());
    }
}
