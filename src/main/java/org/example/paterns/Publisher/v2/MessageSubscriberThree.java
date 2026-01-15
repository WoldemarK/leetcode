package org.example.paterns.Publisher.v2;

public class MessageSubscriberThree implements Observer {

    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriberThree :: " + m.message());
    }
}
