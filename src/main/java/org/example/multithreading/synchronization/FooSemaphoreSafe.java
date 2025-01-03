package org.example.multithreading.synchronization;

import java.util.concurrent.Semaphore;

public class FooSemaphoreSafe {

    private static final Integer counter = 0;

    private final Semaphore firstAndSecond = new Semaphore(counter);
    private final Semaphore secondAndThird = new Semaphore(counter);

    public void first() {
        System.out.println("first");
        firstAndSecond.release();
    }

    public void second() {
        try {
            firstAndSecond.acquire();
            System.out.println("second");
            secondAndThird.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void third() {
        try {
            secondAndThird.acquire();
            System.out.println("third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
