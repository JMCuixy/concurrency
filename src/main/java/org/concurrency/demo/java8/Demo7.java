package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * thenRun 方法：跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo7 {

    public static void main(String[] args) {
        // public CompletionStage<Void> thenRun(Runnable action);
        // public CompletionStage<Void> thenRunAsync(Runnable action);
        // public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);

        CompletableFuture<Void> thenRun = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenRun(() -> System.out.println(123));

    }
}
