package org.concurrency.demo.java8;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * thenAcceptBoth：当两个 CompletionStage 都执行完成后，把结果一块交给 thenAcceptBoth 来进行消耗。
 * 和 thenCombine 的区别在于，thenAcceptBoth 无返回结果
 *
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo9 {

    public static void main(String[] args) {
        // public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
        // public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
        // public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);

        CompletableFuture<Void> thenAcceptBoth = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> "str"),
                        (i, s) -> System.out.println(i + s));
    }
}
