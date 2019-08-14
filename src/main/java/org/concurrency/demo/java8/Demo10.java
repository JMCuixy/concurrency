package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * applyToEither 方法：两个 CompletionStage，谁执行返回的结果快，我就用那个 CompletionStage 的结果进行下一步的转化操作。
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
        // public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
        // public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);

        CompletableFuture<Integer> applyToEither = CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + nextInt);
            return nextInt;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            int nextInt = new Random().nextInt(10);
            try {
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + nextInt);
            return nextInt;
        }), i -> i);

        System.out.println(applyToEither.get());
    }
}
