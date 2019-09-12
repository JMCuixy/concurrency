package org.concurrency.demo.chapter8;

import java.util.concurrent.CountDownLatch;

/**
 * @author : cuixiuyin
 * @date : 2019/9/9
 */
public class CountDownLatchTest {

    // 1. CountDownLatch 允许一个或多个线程等待其他线程完成操作。
    // 2. CountDownLatch 可以替代 join 的作用，并提供了更丰富的用法。
    // 3. CountDownLatch 内部由 AQS 共享锁实现。
    // 4. 当我们调用 CountDownLatch 的 countDown 方法时，N就会减1，CountDownLatch 的 await 方法会阻塞当前线程，直到 N 变成零
    // 5. CountDownLatch不可能重新初始化或者修改CountDownLatch对象的内部计数器的值。
    // 6. 一个线程调用countDown方法happen-before，另外一个线程调用await方法。

    private static final CountDownLatch DOWN_LATCH = new CountDownLatch(2);


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(1);
            DOWN_LATCH.countDown();
            System.out.println(2);
            DOWN_LATCH.countDown();

        }).start();
        DOWN_LATCH.await();
        System.out.println("3");
    }
}
