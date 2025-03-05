package org.example.threads;

public class Result2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(task());
        t.start();
        Thread.sleep(3000);
        t.interrupted();
        t.join(1000);

    }


    private static  Runnable task() {
        return () -> {
            for (int i = 1; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    break;
                }
            }
        };
    }
}
