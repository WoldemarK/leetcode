package org.example.multithreading.threadsintro.demoMain;

import org.example.threadsintro.ThreadCounterWithPriorityWorker;

public class ThreadCounterWithPriorityDemo {
    public static void main(String[] args) {

        ThreadCounterWithPriorityWorker thread1 = new ThreadCounterWithPriorityWorker("A", 5, 10);
        ThreadCounterWithPriorityWorker thread2 = new ThreadCounterWithPriorityWorker("B", 5, 1);

        thread1.start();
        thread2.start();

        System.out.println("Process is finished!!!");
    }
}
