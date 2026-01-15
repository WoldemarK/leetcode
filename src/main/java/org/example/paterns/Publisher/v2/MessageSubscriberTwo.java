package org.example.paterns.Publisher.v2;

public class MessageSubscriberTwo implements Observer {

    @Override
    public void update(Message m) {
        System.out.printf("MessageSubscriberTwo :: %s%n", m.message());
    }
}
