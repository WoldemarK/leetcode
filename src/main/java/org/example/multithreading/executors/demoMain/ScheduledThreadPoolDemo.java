package org.example.multithreading.executors.demoMain;

import org.example.multithreading.executors.GenerateRandomIntegerWithIdTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(final String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();

        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(cores - 1)) {

            GenerateRandomIntegerWithIdTask task1 = new GenerateRandomIntegerWithIdTask(1);
            GenerateRandomIntegerWithIdTask task2 = new GenerateRandomIntegerWithIdTask(2);
            GenerateRandomIntegerWithIdTask task3 = new GenerateRandomIntegerWithIdTask(3);
            GenerateRandomIntegerWithIdTask task4 = new GenerateRandomIntegerWithIdTask(4);
            GenerateRandomIntegerWithIdTask task5 = new GenerateRandomIntegerWithIdTask(5);

            executor.schedule(task2, 10, TimeUnit.SECONDS);
            executor.schedule(task1, 3, TimeUnit.SECONDS);
            executor.schedule(task3, 2, TimeUnit.SECONDS);
            executor.schedule(task4, 1, TimeUnit.SECONDS);
            executor.schedule(task5, 0, TimeUnit.SECONDS);

        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Processed in: " + duration + " ms");
        }
    }
}
