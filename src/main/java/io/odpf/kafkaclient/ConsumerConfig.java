package io.odpf.kafkaclient;

public interface ConsumerConfig extends CoreConfig {


    @Key("group.id")
    @DefaultValue("test")
    String getGroupID();

    @Key("enable.auto.commit")
    @DefaultValue("true")
    boolean getEnableAutoCommit();

    @Key("auto.commit.interval.ms")
    @DefaultValue("1000")
    int getAutoCommitIntervalMs();

    @Key("session.timeout.ms")
    @DefaultValue("1000")
    String getSessionTimeoutMs();

    @Key("key.deserializer")
    @DefaultValue("org.apache.kafka.common.serialization.StringDeserializer")
    Class getKeyDeserializer();

    @Key("value.deserializer")
    @DefaultValue("org.apache.kafka.common.serialization.StringDeserializer")
    Class getValueDeserializer();

}
