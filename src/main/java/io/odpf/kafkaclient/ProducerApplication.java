package io.odpf.kafkaclient;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class ProducerApplication implements Application {

    private final KafkaProducer<String, String> kafkaProducer;
    private final BufferedReader bufferedReader;
    private Properties properties;

    public ProducerApplication(ProducerConfig producerConfig) {
        this.kafkaProducer = new KafkaProducer<String, String>(createProperties(producerConfig));
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    public Properties createProperties(ProducerConfig producerConfig) {


        properties = new Properties();
        properties.put("bootstrap.servers", producerConfig.getBootstrapServers());
        properties.put("acks", producerConfig.getAcks());
        properties.put("retries", producerConfig.getRetries());
        properties.put("batch.size", producerConfig.getBatchSize());
        properties.put("linger.ms", producerConfig.getLingerMs());
        properties.put("key.serializer", producerConfig.getKeySerializer());
        properties.put("value.serializer", producerConfig.getValueSerializer());

        return properties;


    }

    @Override
    public void execute() {

        try {
            LOGGER.info("Enter input in the format - <topic> <key> <value> . Key is optional");

            while (true) {
                String inputString = bufferedReader.readLine();
                String[] inputStringTokens = inputString.split(" ");

                writeEvent(inputStringTokens);
            }
        } catch (Exception e) {

        }
    }


    void writeEvent(String... eventData) {
        String topic = eventData[0];
        String value = eventData[eventData.length - 1];

        switch (eventData.length) {
            case 2: {
                kafkaProducer.send(new ProducerRecord<>(topic, value));
                return;
            }
            case 3: {
                String key = eventData[1];
                kafkaProducer.send(new ProducerRecord<>(topic, key, value));
                return;
            }
        }
    }

}
