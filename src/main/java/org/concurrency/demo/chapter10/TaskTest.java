package org.concurrency.demo.chapter10;

import java.util.concurrent.*;

/**
 * @author : cuixiuyin
 * @date : 2019/9/19
 */
public class TaskTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Runnable 和 Callable 都可以直接被 ThreadPoolExecutor 和 ScheduledThreadPoolExecutor 执行
        Runnable runnable = () -> System.out.println(123);
        // Executors 可以将 Runnable 转化成 Callable
        Callable<Object> callable = Executors.callable(runnable);
        Callable<String> success = Executors.callable(runnable, "success");

        // 执行 Runnable
        executorService.execute(runnable);
        // 执行 Callable
        Future<Object> submit = executorService.submit(callable);
        // Future 的 get 方法会阻塞线程完成
        System.out.println(submit.get());
        Future<String> future = executorService.submit(success);
        System.out.println(future.get());
    }
}
