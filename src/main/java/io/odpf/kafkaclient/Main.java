package io.odpf.kafkaclient;

public class Main {


    public static void main(String... args) {
        try {
            ApplicationType.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            args[0] = "INVALID";
        }
        Application app = ProducerConsumerFactory.createApplication(
                ApplicationType.valueOf(args[0]), args[1]);
        app.execute();
    }
}
