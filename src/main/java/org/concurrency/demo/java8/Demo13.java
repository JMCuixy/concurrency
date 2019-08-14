package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * runAfterBoth：两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo13 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
        // public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
        // public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);

        CompletableFuture<Void> acceptEither = CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + nextInt);
            return nextInt;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + nextInt);
            return nextInt;
        }), () -> System.out.println("两个方法都执行完成了"));

        acceptEither.get();
    }
}
