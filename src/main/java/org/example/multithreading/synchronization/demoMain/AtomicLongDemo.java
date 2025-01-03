package org.example.multithreading.synchronization.demoMain;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongDemo {

    private static final AtomicLong atomicLongCounter = new AtomicLong(0);

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                increment();
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("atomicLongCounter: " + atomicLongCounter.get());
    }

    public static void increment() {
        atomicLongCounter.incrementAndGet();
    }
}
