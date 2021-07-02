package io.odpf.kafkaclient;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerApplication implements Application{

    private final KafkaConsumer<String, String> kafkaConsumer;
    private final BufferedReader bufferedReader;
    private ArrayList<String> topicList;
    private Properties properties;

    public ConsumerApplication(ConsumerConfig consumerConfig) {
        this.kafkaConsumer = new KafkaConsumer<>(createProperties(consumerConfig));
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    public Properties createProperties(ConsumerConfig consumerConfig) {


        properties = new Properties();
        properties.put("bootstrap.servers",consumerConfig.getBootstrapServers());
        properties.put("group.id",consumerConfig.getGroupID());
        properties.put("enable.auto.commit",consumerConfig.getEnableAutoCommit());
        properties.put("auto.commit.interval.ms",consumerConfig.getAutoCommitIntervalMs());
        properties.put("key.deserializer",consumerConfig.getKeyDeserializer());
        properties.put("value.deserializer",consumerConfig.getValueDeserializer());

        return properties;


    }
    ArrayList<String> readEvent() {
        ArrayList<String> recordList = new ArrayList<>();
        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));

        for (ConsumerRecord<String, String> record : records)
            recordList.add("topicList=" + record.topic() + " key=" + record.key() + " value=" + record.value());
        return recordList;
    }

    public void subscribe(ArrayList<String> topicList) {
        this.topicList = topicList;
        kafkaConsumer.subscribe(this.topicList);
    }

    @Override
    public void execute() {
        LOGGER.info("Enter topics to subscribe to");
        try {
            subscribe(new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(" "))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            readEvent().forEach((record) -> LOGGER.info(record + "\n"));
        }
    }
}
