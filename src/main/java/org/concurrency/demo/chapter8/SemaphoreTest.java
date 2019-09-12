package org.concurrency.demo.chapter8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : cuixiuyin
 * @date : 2019/9/11
 */
public class SemaphoreTest {

    // 1. Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
    // 2. Semaphore 可以用于做流量控制，特别是公用资源有限的应用场景，比如数据库连接。
    // 3. Semaphore的构造方法 Semaphore（int permits）接受一个整型的数字，表示可用的许可证数量。
    // 4. 首先线程使用 Semaphore 的 acquire() 方法获取一个许可证，使用完之后调用 release() 方法归还许可证。还可以用 tryAcquire() 方法尝试获取许可证。
    // 5. intavailablePermits()：返回此信号量中当前可用的许可证数。
    // 6. intgetQueueLength()：返回正在等待获取许可证的线程数。
    // 7. booleanhasQueuedThreads()：是否有线程正在等待获取许可证。

    private static final int THREAD_COUNT = 30;
    private static ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore SEMAPHORE = new Semaphore(10);
    private static AtomicInteger ATOMICINTEGER = new AtomicInteger(1);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            EXECUTOR.execute(() -> {
                try {
                    SEMAPHORE.acquire();
                    System.out.println("save data" + ATOMICINTEGER.getAndIncrement());
                    SEMAPHORE.release();
                } catch (InterruptedException e) {
                }

            });
        }
        EXECUTOR.shutdown();
    }
}
