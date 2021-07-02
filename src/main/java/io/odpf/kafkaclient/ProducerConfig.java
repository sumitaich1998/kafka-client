package io.odpf.kafkaclient;

public interface ProducerConfig extends CoreConfig{


    @Key("acks")
    @DefaultValue("all")
    String getAcks();

    @Key("retries")
    @DefaultValue("0")
    String getRetries();

    @Key("batch.size")
    @DefaultValue("16384")
    int getBatchSize();

    @Key("linger.ms")
    @DefaultValue("1")
    int getLingerMs();

    @Key("key.serializery")
    @DefaultValue("org.apache.kafka.common.serialization.StringSerializer")
    String getKeySerializer();

    @Key("value.serializer")
    @DefaultValue("org.apache.kafka.common.serialization.StringSerializer")
    String getValueSerializer();

}
