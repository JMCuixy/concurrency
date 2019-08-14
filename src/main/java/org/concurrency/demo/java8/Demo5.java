package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * handle 方法：handle 是执行任务完成时对结果的处理
 * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执
 * 行正常的任务，任务出现异常则不执行 thenApply 方法。
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
        // public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
        // public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);

        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return new Random().nextInt(10);
        }).handle((t, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                return -1;
            }
            return t * t;
        });
        System.out.println(handle.get());
    }
}
