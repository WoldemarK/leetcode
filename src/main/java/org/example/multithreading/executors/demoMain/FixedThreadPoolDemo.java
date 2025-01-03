package org.example.multithreading.executors.demoMain;

import org.example.executors.GenerateRandomIntegerTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newFixedThreadPool(100)) {
            for (int i = 0; i < 100; i++) {
                executorService.submit(new GenerateRandomIntegerTask());
            }
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Processed in: " + duration + " ms" + " cores " + cores );
        }
    }
}
