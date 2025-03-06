package org.example.paterns.Publisher;

public class NotificationListener implements EventListener {
    @Override
    public void onEvent(String event) {
        System.out.printf("class NotificationListener %s%n", event);
    }
}
