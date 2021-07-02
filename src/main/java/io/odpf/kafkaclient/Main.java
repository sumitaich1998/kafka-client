package io.odpf.kafkaclient;

public class Main {


    public static void main(String... args) {
        Application app = ProducerConsumerFactory.createApplication(
                ApplicationType.valueOf(args[0]), args[1]);
        app.execute();
    }
}
