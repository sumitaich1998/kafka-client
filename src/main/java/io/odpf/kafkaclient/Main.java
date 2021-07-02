package io.odpf.kafkaclient;

public class Main {


    public static void main(String... args) {
        Application app = ProducerConsumerFactory.createApplication(args);
        app.execute();
    }
}
