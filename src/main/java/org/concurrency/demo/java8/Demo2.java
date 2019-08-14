package org.concurrency.demo.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * runAsync 和 supplyAsync方法创建一个异步操作
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public static CompletableFuture<Void> runAsync(Runnable runnable)
        // public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
        // public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
        // public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)

        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println(123));

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> "CompletableFuture");
        System.out.println(supplyAsync.get());
    }
}
