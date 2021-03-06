package io.odpf.kafkaclient.producer;

import io.odpf.kafkaclient.Client;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
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

    Future<RecordMetadata> writeEvent(String... eventData) {
        String topic = eventData[0];
        String value = eventData[eventData.length - 1];

        switch (eventData.length) {
            case 2: {
                return kafkaProducer.send(new ProducerRecord<>(topic, value));
            }
            case 3: {
                String key = eventData[1];
                return kafkaProducer.send(new ProducerRecord<>(topic, key, value));
            }
            default:
                throw new InvalidNumberOfArgumentsException("Number of arguments should be 2 or 3");
        }
    }

    @Override
    public void interact() throws IOException {
        getLog().info("Enter input in the format - <topic> <key> <value> . Key is optional");

        while (true) {
            String inputString = bufferedReader.readLine();
            String[] inputStringTokens = inputString.split(" ");

            writeEvent(inputStringTokens);
        }
    }
}
