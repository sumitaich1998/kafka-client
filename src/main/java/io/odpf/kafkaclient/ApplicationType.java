package io.odpf.kafkaclient;

public enum ApplicationType {

    PRODUCER, CONSUMER;

    public Class getConfigClass() {
        if (this == PRODUCER) return ProducerConfig.class;
        else return ConsumerConfig.class;


    }
}
