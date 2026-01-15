package org.example.paterns.Publisher.v2;

public class Mai {
    public static void main(String[] args) {

        Observer ob = new MessageSubscriberTwo();
        Observer ob2 = new MessageSubscriberThree();
        Observer ob3 = new MessageSubscriberOne();

        MessagePublisher m = new MessagePublisher();
        m.attach(ob);
        m.attach(ob2);
        m.attach(ob3);

        m.notifyUpdate(new Message("kljkljlkj"));
    }
}
