package org.example.multithreading.synchronization;

public class SyncThread implements Runnable {
    private final Object lock1;
    private final Object lock2;

    public SyncThread(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        System.out.println("Thread: " + name + " acquiring lock on " + lock1);
        synchronized (lock1) {
            System.out.println(name + " acquired lock on " + lock1);
            work();

            System.out.println(name + " acquiring lock on " + lock2);
            synchronized (lock2) {
                System.out.println(name + " acquired lock on " + lock2);
                work();
            }
            System.out.println(name + " released lock on " + lock2);
        }
        System.out.println(name + " released lock on " + lock1);
        System.out.println(name + " finished execution.");
    }

    private void work() {
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
