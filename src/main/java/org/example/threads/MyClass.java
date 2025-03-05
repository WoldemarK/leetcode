package org.example.threads;

public class MyClass implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("Thread started: " + t.getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        System.out.println("Thread end: " + t.getName());
    }
}

class Result {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyClass(), "t1");
        Thread t2 = new Thread(new MyClass(), "t2");

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException i) {
            i.printStackTrace();
        }
        t2.start();
    }
}