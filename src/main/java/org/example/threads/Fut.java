package org.example.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Fut {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello world!")
                .thenApply(s -> s + "kkkk");

        System.out.println(future.get());
    }
}
