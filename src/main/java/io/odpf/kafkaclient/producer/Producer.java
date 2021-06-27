package io.odpf.kafkaclient.producer;

import io.odpf.kafkaclient.Client;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Future;

public class Producer extends Client {

    private final KafkaProducer<String, String> kafkaProducer;
    private final BufferedReader bufferedReader;

    public Producer(KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Future<RecordMetadata> writeEvent(String... eventData) {
        throw new InvalidNumberOfArgumentsException();
    }

    @Override
    public void interact() throws IOException {

    }
}
