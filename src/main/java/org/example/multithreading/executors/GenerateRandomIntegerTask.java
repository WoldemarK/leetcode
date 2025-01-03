package org.example.multithreading.executors;

import java.util.Random;

public class GenerateRandomIntegerTask implements Runnable {

    private final Random random = new Random();

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            int randomInt = random.nextInt(100) + 1;
            System.out.println("SingleThreadPoolTask: " + randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
