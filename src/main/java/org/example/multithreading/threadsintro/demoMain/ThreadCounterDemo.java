package org.example.multithreading.threadsintro.demoMain;

import lombok.SneakyThrows;
import org.example.threadsintro.ThreadCounterWorker;

public class ThreadCounterDemo {
    @SneakyThrows
    public static void main(String[] args) {
        ThreadCounterWorker tcw1 = new ThreadCounterWorker("A", 100);
        ThreadCounterWorker tcw2 = new ThreadCounterWorker("B", 100);

        tcw1.start();
        Thread.sleep(1000);
        tcw2.start();
    }
}
