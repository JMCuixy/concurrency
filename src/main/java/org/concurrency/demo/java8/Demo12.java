package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * runAfterEither : 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo12 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
        // public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action);
        // public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor);

        CompletableFuture<Void> acceptEither = CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + nextInt);
            return nextInt;
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + nextInt);
            return nextInt;
        }), System.out::println);

        acceptEither.get();
    }
}
