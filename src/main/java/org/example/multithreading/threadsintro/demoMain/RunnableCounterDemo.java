package org.example.multithreading.threadsintro.demoMain;

import lombok.SneakyThrows;
import org.example.threadsintro.RunnableCounterWorker;

public class RunnableCounterDemo {

    @SneakyThrows
    public static void main(String[] args) {

        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());

        RunnableCounterWorker worker1 = new RunnableCounterWorker("A", 15);
        RunnableCounterWorker worker2 = new RunnableCounterWorker("B", 15);

        Thread t1 = new Thread(worker1);
        Thread t2 = new Thread(worker2);

        t1.start();
        t2.start();

    }
}
