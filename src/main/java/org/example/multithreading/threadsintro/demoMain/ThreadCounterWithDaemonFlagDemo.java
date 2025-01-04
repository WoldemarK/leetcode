package org.example.multithreading.threadsintro.demoMain;

import org.example.multithreading.threadsintro.ThreadCounterWithDaemonFlagWorker;

public class ThreadCounterWithDaemonFlagDemo {
    public static void main(String[] args) {

        ThreadCounterWithDaemonFlagWorker thread1 = new ThreadCounterWithDaemonFlagWorker("A",10,true);
        ThreadCounterWithDaemonFlagWorker thread2 = new ThreadCounterWithDaemonFlagWorker("B",5,false);

        thread1.start();
        thread2.start();

        System.out.println("Process is finished!!!");
    }
}
