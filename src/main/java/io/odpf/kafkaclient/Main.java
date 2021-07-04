package io.odpf.kafkaclient;

public class Main {


    public static void main(String... args) {
        Application app = ProducerConsumerFactory.createApplication(
                ApplicationType.valueOf(args[0].toUpperCase()), args[1]);
        app.execute();
    }
}
