package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenCombine 合并任务: thenCombine 会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo8 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
        // public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
        // public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
        CompletableFuture<String> thenCombine = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenCombine(CompletableFuture.supplyAsync(() -> "str"),
                        // 第一个参数是第一个 CompletionStage 的处理结果
                        // 第二个参数是第二个 CompletionStage 的处理结果
                        (i, s) -> i + s
                );
        System.out.println(thenCombine.get());
    }
}
