package org.concurrency.demo.java8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Future 的基本用法
 *
 * @author : cuixiuyin
 * @date : 2019/8/12
 */
public class Demo15 {

    private static int TASK_THRESHOLD = Runtime.getRuntime().availableProcessors();

    static {
        TASK_THRESHOLD = TASK_THRESHOLD < 4 ? TASK_THRESHOLD : 4;
        TASK_THRESHOLD = TASK_THRESHOLD - 1 > 0 ? TASK_THRESHOLD - 1 : 1;
    }

    private static final ExecutorService POOL = Executors.newFixedThreadPool(TASK_THRESHOLD, new ThreadFactory() {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "demo15-" + atomicInteger.incrementAndGet());
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Integer> submit = POOL.submit(() -> 123);
        // 1. get() 方法用户返回计算结果，如果计算还没有完成，则在get的时候会进行阻塞，直到获取到结果为止
        Integer get = submit.get();
        System.out.println(get);
        // 2. isDone() 方法用于判断当前Future是否执行完成。
        boolean done = submit.isDone();
        System.out.println(done);
        // 3. cancel(boolean mayInterruptIfRunning) 取消当前线程的执行。参数表示是否在线程执行的过程中阻断。
        boolean cancel = submit.cancel(true);
        System.out.println(cancel);
        // 4. isCancelled() 判断当前task是否被取消.
        boolean cancelled = submit.isCancelled();
        System.out.println(cancelled);
        // 5. invokeAll 批量执行任务
        Callable<String> callable = () -> "Hello Future";
        List<Callable<String>> callables = Lists.newArrayList(callable, callable, callable, callable);
        List<Future<String>> futures = POOL.invokeAll(callables);
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }
}
