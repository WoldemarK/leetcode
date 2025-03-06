package org.example.paterns.Publisher;

public class MainPublisher {
    public static void main(String[] args) {

        EventPublisher publisher = new EventPublisher();
        EventListener loggingListener = new LoggingListener();
        EventListener notificationListener = new NotificationListener();

        publisher.addListener(loggingListener);
        publisher.addListener(notificationListener);

        publisher.notifyListeners("Hello World");

        publisher.removeListener(loggingListener);

        publisher.notifyListeners("Hello World2222");
    }
}
