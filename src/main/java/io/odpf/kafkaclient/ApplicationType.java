package io.odpf.kafkaclient;

public enum ApplicationType {

    PRODUCER, CONSUMER, INVALID;

    public Class<? extends CoreConfig> getConfigClass() {
        if (this == PRODUCER) return ProducerConfig.class;
        else return ConsumerConfig.class;


    }
}
