package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * acceptEither 方法 : 两个 CompletionStage，谁执行返回的结果快，我就用那个 CompletionStage 的结果进行下一步的消耗操作。
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo11 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
        // public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action);
        // public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor);

        CompletableFuture<Void> acceptEither = CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + nextInt);
            return nextInt;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
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
