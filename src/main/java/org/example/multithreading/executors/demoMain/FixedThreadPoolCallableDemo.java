//package org.example.multithreading.executors.demoMain;
//
//import org.example.multithreading.executors.GenerateRandomIntegerCallableTask;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//public class FixedThreadPoolCallableDemo {
//    public static void main(final String[] args) {
//
//        int cores = Runtime.getRuntime().availableProcessors();
//        List<Future<Integer>> futures = new ArrayList<>();
//        long start = System.currentTimeMillis();
//
//        try (ExecutorService executor = Executors.newFixedThreadPool(cores * 100)) {
//            for (int i = 0; i < 100; i++) {
//                futures.add(executor.submit(new GenerateRandomIntegerCallableTask()));
//            }
//        }
//        futures.forEach(future -> {
//            try {
//                System.out.println(future.get());
//            }catch (InterruptedException | ExecutionException e){
//                throw new RuntimeException(e);
//            }
//        });
//        long end = System.currentTimeMillis();
//        long duration = end - start;
//        System.out.println("Processed in: " + duration + " ms");
//    }
//}
