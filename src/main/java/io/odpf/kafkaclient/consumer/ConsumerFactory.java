package io.odpf.kafkaclient.consumer;

import io.odpf.kafkaclient.ClientFactory;
import io.odpf.kafkaclient.PropertiesNotSetException;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerFactory extends ClientFactory {

    @Override
    public Consumer create() {
        if (getProperties() == null)
            throw new PropertiesNotSetException("Properties have not been configured");
        return new Consumer(new KafkaConsumer<>(getProperties()));
    }
}
