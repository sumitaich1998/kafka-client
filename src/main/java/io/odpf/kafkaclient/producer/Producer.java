package io.odpf.kafkaclient.producer;

import io.odpf.kafkaclient.Client;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Producer extends Client {

    private final KafkaProducer<String, String> kafkaProducer;
    private final BufferedReader bufferedReader;

    public Producer(KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void interact() throws IOException {

    }
}
