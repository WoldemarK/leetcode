package org.example.multithreading.collections.demoMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionThreadSafeDemo {
    public static void main(final String[] args) {
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                integers.add(i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                integers.add(i);
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadUnsafeList size: " + integers.size());
    }
}
