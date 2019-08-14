package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenCompose 方法: 允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo14 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
        // public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) ;
        // public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) ;

        CompletableFuture<Integer> thenCompose = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i * i));
        System.out.println(thenCompose.get());

    }
}
