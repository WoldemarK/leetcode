package org.example.paterns.Publisher;

public class LoggingListener implements EventListener {
    @Override
    public void onEvent(String event) {
        System.out.printf("class LoggingListener %s%n", event);
    }
}
