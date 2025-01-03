package org.example.multithreading.collections;

import java.util.List;
import java.util.Random;

public class WriteToCOWArrayWorker implements Runnable {

    private final List<Integer> list;
    private final Random rand = new Random();

    public WriteToCOWArrayWorker(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                this.list.add(rand.nextInt(list.size()), rand.nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
