package org.example.multithreading.synchronization.demoMain;

import org.example.multithreading.synchronization.Eater;
import org.example.multithreading.synchronization.Spoon;

public class LiveLockDemo {
    public static void main(String[] args) {

        final Eater husband = new Eater("Bob");
        final Eater wife = new Eater("Alice");

        final Spoon s = new Spoon(wife);

        new Thread(() -> husband.eatWith(s, wife)).start();
        new Thread(() -> wife.eatWith(s, husband)).start();
    }
}
