package io.odpf.kafkaclient.consumer;

import io.odpf.kafkaclient.Client;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

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
        if (this.topic == null || !(this.topic.equals(topic))) {
            this.topic = topic;
            kafkaConsumer.subscribe(Collections.singletonList(topic));
        }
        ArrayList<String> recordList = new ArrayList<>();
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10));

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (ConsumerRecord<String, String> record : records)
            recordList.add(record.topic() + " " + record.key() + " " + record.value());
        return recordList;
    }

    @Override
    public void interact() throws IOException {
        getLog().config("Enter topic to subscribe to");
        String topic = bufferedReader.readLine();

        while (true) {
            readEvent(topic).forEach((record) -> getLog().info(record + "\n"));
        }
    }
}
