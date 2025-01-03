package org.example.multithreading.synchronization.demoMain;

import org.example.synchronization.FooUnsafe;

public class FooUnsafeDemo {
    public static void main(String[] args) {
        FooUnsafe foo = new FooUnsafe();
        Thread thread1 = new Thread(foo::first);
        Thread thread2 = new Thread(foo::second);
        Thread thread3 = new Thread(foo::third);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
