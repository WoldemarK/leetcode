package org.example.multithreading.collections;

import java.util.concurrent.BlockingQueue;

public class ReadFromPriorityQueueWorker implements Runnable {

    private final BlockingQueue<Integer> queue;

    public ReadFromPriorityQueueWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3_000);
            System.out.println("Take: " + queue.take());
            System.out.println("Take: " + queue.take());
            System.out.println("Take: " + queue.take());
            System.out.println("Take: " + queue.take());
            System.out.println("Take: " + queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
