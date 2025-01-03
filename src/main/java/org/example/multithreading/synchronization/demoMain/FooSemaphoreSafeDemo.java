package org.example.multithreading.synchronization.demoMain;

import org.example.synchronization.FooSemaphoreSafe;

public class FooSemaphoreSafeDemo {
    public static void main(String[] args) {

        FooSemaphoreSafe safe = new FooSemaphoreSafe();

        Thread t1 = new Thread(safe::second);
        Thread t2 = new Thread(safe::first);
        Thread t3 = new Thread(safe::third);

        t1.setName("T1");
        t2.setName("T2");
        t3.setName("T3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
