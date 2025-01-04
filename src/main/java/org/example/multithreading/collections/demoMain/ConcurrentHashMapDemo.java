package org.example.multithreading.collections.demoMain;

import org.example.multithreading.collections.ReadFromCHMWorker;
import org.example.multithreading.collections.WriteToCHMWorker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        WriteToCHMWorker writeToCHMWorker = new WriteToCHMWorker(concurrentMap);
        ReadFromCHMWorker readFromCHMWorker = new ReadFromCHMWorker(concurrentMap);

        Thread t1 = new Thread(writeToCHMWorker);
        Thread t2 = new Thread(writeToCHMWorker);
        Thread t3 = new Thread(readFromCHMWorker);

        t1.start();
        t2.start();
        t3.start();

    }
}

