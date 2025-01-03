package org.example.multithreading.synchronization.demoMain;

import lombok.SneakyThrows;

public class IncrementCounterUnsafeDemo {

    static volatile Integer counter = 0;
    static volatile Integer anotherCounter = 0;

    @SneakyThrows
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementCounter();
                System.out.println(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                incrementAnotherCounter();
                System.out.println(i);
            }
        });

        thread1.start();
        Thread.sleep(1000);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Counter: " + counter);
        System.out.println("Another counter: " + anotherCounter);
        System.out.println("Time elapsed: " + duration);
    }


    private static void incrementCounter() {
        synchronized (IncrementCounterUnsafeDemo.class) {
            counter++;
        }
    }

    private static void incrementAnotherCounter() {
        synchronized (IncrementCounterUnsafeDemo.class) {
            anotherCounter++;
        }
    }
}
