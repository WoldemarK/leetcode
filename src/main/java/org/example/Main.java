package org.example;

public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(() ->
        {
            try {
                Thread.sleep(60);
                System.out.println("Waked up");
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        });
        thread.start();
        thread.interrupt();

    }
}


