package io.odpf.kafkaclient;

/**
 * The type Producer consumer factory.
 */
public class ProducerConsumerFactory {

    /**
     * Create application application.
     *
     * @param applicationType the application type
     * @param configPath      the config path
     * @return the application
     */
    public static Application createApplication(ApplicationType applicationType,String configPath) {

        switch (applicationType) {
            case PRODUCER:
                return new ProducerApplication((ProducerConfig) new ConfigParser(configPath,applicationType).parse());
            case CONSUMER:
                return new ConsumerApplication((ConsumerConfig) new ConfigParser(configPath,applicationType).parse());

        }
        return null;
    }
}
