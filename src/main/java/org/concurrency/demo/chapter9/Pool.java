package org.concurrency.demo.chapter9;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author : cuixiuyin
 * @date : 2019/9/17
 */
public class Pool {

    static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("pool-task-%d").build();
    static ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
            threadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 无返回值的任务执行 -> Runnable
        executor.execute(() -> System.out.println("Hello World"));
        // 2. 有返回值的任务执行 -> Callable
        Future<String> future = executor.submit(() -> "Hello World");
        // get 方法会阻塞线程执行等待返回结果
        String result = future.get();
        System.out.println(result);

        // 3. 监控线程池
        monitor();

        // 4. 关闭线程池
        shutdownAndAwaitTermination();

        monitor();
    }

    private static void monitor() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Pool.executor;
        System.out.println("【线程池任务】线程池中曾经创建过的最大线程数：" + threadPoolExecutor.getLargestPoolSize());
        System.out.println("【线程池任务】线程池中线程数：" + threadPoolExecutor.getPoolSize());
        System.out.println("【线程池任务】线程池中活动的线程数：" + threadPoolExecutor.getActiveCount());
        System.out.println("【线程池任务】队列中等待执行的任务数：" + threadPoolExecutor.getQueue().size());
        System.out.println("【线程池任务】线程池已执行完任务数：" + threadPoolExecutor.getCompletedTaskCount());
    }

    /**
     * 关闭线程池
     * 1. shutdown、shutdownNow 的原理都是遍历线程池中的工作线程，然后逐个调用线程的 interrupt 方法来中断线程。
     * 2. shutdownNow：将线程池的状态设置成 STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表。
     * 3. shutdown：将线程池的状态设置成 SHUTDOWN 状态，然后中断所有没有正在执行任务的线程。
     */
    private static void shutdownAndAwaitTermination() {
        // 禁止提交新任务
        executor.shutdown();
        try {
            // 等待现有任务终止
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                // 取消当前正在执行的任务
                executor.shutdownNow();
                // 等待一段时间让任务响应被取消
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // 如果当前线程也中断，则取消
            executor.shutdownNow();
            // 保留中断状态
            Thread.currentThread().interrupt();
        }
    }
}
