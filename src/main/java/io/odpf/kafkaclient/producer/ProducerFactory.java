package io.odpf.kafkaclient.producer;

import io.odpf.kafkaclient.ClientFactory;
import io.odpf.kafkaclient.PropertiesNotSetException;
import org.apache.kafka.clients.producer.KafkaProducer;

public class ProducerFactory extends ClientFactory {

    @Override
    public Producer create() {
        if (getProperties() == null)
            throw new PropertiesNotSetException("Properties have not been configured");
        return new Producer(new KafkaProducer<>(getProperties()));
    }
}
