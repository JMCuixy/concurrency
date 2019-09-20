package org.concurrency.demo.chapter10;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @author : cuixiuyin
 * @date : 2019/9/21
 */
public class ScheduleTaskTest {

    static ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").build();
    static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5, threadFactory);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 延迟 3 秒后执行 Runnable 方法
        scheduledExecutorService.schedule(() -> System.out.println("Hello World"), 3000, TimeUnit.MILLISECONDS);

        // 2. 延迟 3 秒后执行 Callable 方法
        ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(() -> "Hello ScheduledFuture", 3000, TimeUnit.MILLISECONDS);
        System.out.println(scheduledFuture.get());

        // 3. 延迟 1 秒后开始每隔 3 秒周期执行。
        //    如果中间任务遇到异常，则禁止后续执行。
        //    固定的频率来执行某项任务，它不受任务执行时间的影响。到时间，就执行。
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Hello ScheduleAtFixedRate"), 1, 3000, TimeUnit.MILLISECONDS);

        // 4. 延迟 1 秒后，每个任务结束延迟 3 秒后再执行下个任务。
        //    如果中间任务遇到异常，则禁止后续执行。
        //    受任务执行时间的影响，等待任务执行结束后才开始计算延迟。
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Hello ScheduleWithFixedDelay"), 1, 3000, TimeUnit.MILLISECONDS);
    }
}
