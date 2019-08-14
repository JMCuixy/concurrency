package org.concurrency.demo.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenApply : 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
        // public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
        // public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
        // T：上一个任务返回结果的类型
        // U：当前任务的返回值类型

        CompletableFuture<Integer> thenApply = CompletableFuture.supplyAsync(() -> 123).thenApply(t -> t * t);
        System.out.println(thenApply.get());
    }
}
