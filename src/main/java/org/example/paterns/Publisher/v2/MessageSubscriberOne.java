package org.example.paterns.Publisher.v2;

public class MessageSubscriberOne implements Observer {

    @Override
    public void update(Message m) {
        System.out.printf("MessageSubscriberOne :: %s%n", m.message());
    }
}
