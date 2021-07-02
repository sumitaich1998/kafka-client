package io.odpf.kafkaclient;

import org.aeonbits.owner.Config;

public interface CoreConfig extends Config {

    @Key("bootstrap.servers")
    @DefaultValue("localhost:9092")
    String getBootstrapServers();

}
