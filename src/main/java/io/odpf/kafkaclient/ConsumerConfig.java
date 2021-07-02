package io.odpf.kafkaclient;

public interface ConsumerConfig extends CoreConfig {


    @Key("group.id")
    @DefaultValue("test")
    String getGroupID();

    @Key("enable.auto.commit")
    @DefaultValue("true")
    String getEnableAutoCommit();

    @Key("auto.commit.interval.ms")
    @DefaultValue("1000")
    String getAutoCommitIntervalMs();

    @Key("session.timeout.ms")
    @DefaultValue("1000")
    String getSessionTimeoutMs();

    @Key("key.deserializer")
    @DefaultValue("org.apache.kafka.common.serialization.StringDeserializer")
    String getKeyDeserializer();

    @Key("value.deserializer")
    @DefaultValue("org.apache.kafka.common.serialization.StringDeserializer")
    String getValueDeserializer();

}
