package io.odpf.kafkaclient.consumer;

import io.odpf.kafkaclient.Client;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class Consumer extends Client {

    private final KafkaConsumer<String, String> kafkaConsumer;
    private final BufferedReader bufferedReader;
    private ArrayList<String> topicList;

    public Consumer(KafkaConsumer<String, String> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    ArrayList<String> readEvent() {
        if (topicList == null) throw new NullTopicException("Topic cannot be null");
        ArrayList<String> recordList = new ArrayList<>();
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));

        for (ConsumerRecord<String, String> record : records)
            recordList.add("topicList=" + record.topic() + " key=" + record.key() + " value=" + record.value());
        return recordList;
    }

    public void subscribe(ArrayList<String> topicList) {
        this.topicList = topicList;
        kafkaConsumer.subscribe(topicList);
    }

    @Override
    public void interact() {
        getLog().info("Enter topics to subscribe to");
        try {
            subscribe(new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(" "))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            readEvent().forEach((record) -> getLog().info(record + "\n"));
        }
    }
}
