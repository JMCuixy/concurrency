package org.concurrency.demo.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 计算结果完成时的回调方法
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 是执行当前任务的线程执行继续执行 whenComplete 的任务。
        // public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
        // 是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
        // public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
        // public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
        // public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)

        CompletableFuture<Integer> runAsync = CompletableFuture.supplyAsync(() -> 123456);
        runAsync.whenComplete((t, throwable) -> {
            System.out.println(t);
            if (throwable != null) {
                throwable.printStackTrace();
            }
        });
        runAsync.whenCompleteAsync((t, throwable) -> {
            System.out.println(t);
            if (throwable != null) {
                throwable.printStackTrace();
            }
        });
        runAsync.exceptionally((throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            }
            return null;
        });
        TimeUnit.SECONDS.sleep(2);
    }
}
