package io.odpf.kafkaclient.consumer;

import io.odpf.kafkaclient.Client;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Consumer extends Client {

    private final KafkaConsumer<String, String> kafkaConsumer;
    private final BufferedReader bufferedReader;
    private String topic;

    public Consumer(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public ArrayList<String> readEvent(String topic) {
        if (topic == null) throw new NullTopicException();
        return new ArrayList<>();
    }

    @Override
    public void interact() throws IOException {

    }
}
