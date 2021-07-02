package io.odpf.kafkaclient;

public class ProducerConsumerFactory {



    public static Application createApplication(ApplicationType applicationType,String configPath) {

        switch (applicationType) {
            case CONSUMER:
                return new ProducerApplication((ProducerConfig) new ConfigParser(configPath,applicationType).parse());
            case PRODUCER:
                return new ConsumerApplication((ConsumerConfig) new ConfigParser(configPath,applicationType).parse());

        }

        return null;
    }
}
