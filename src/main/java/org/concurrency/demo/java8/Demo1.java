package org.concurrency.demo.java8;

import java.util.concurrent.ForkJoinPool;

/**
 * @author : cuixiuyin
 * @date : 2019/8/11
 */
public class Demo1 {

    public static void main(String[] args) {
        // 默认的设置下它会创建系统硬件支持的线程数一样多的线程（通常就是跟CPU的核心数，如果你的CPU支持超线程，那么可能再翻一倍）
        // CompletableFuture 使用中，如果不指定线程池，将使用该线程池。
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    }
}
