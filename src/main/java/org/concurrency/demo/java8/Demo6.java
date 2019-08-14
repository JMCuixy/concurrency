package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * thenAccept : 接收任务的处理结果，并消费处理，无返回结果
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo6 {

    public static void main(String[] args) {
        // public CompletionStage<Void> thenAccept(Consumer<? super T> action);
        // public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
        // public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);

        CompletableFuture<Void> thenAccept = CompletableFuture.supplyAsync(() -> new Random().nextInt(10)).thenAccept(System.out::println);

    }
}
