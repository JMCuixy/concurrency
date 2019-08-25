package org.concurrency.demo.chapter5;

import java.util.concurrent.CountDownLatch;

/**
 * @author : cuixiuyin
 * @date : 2019/8/25
 */
public class MutexTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static volatile Integer integer = 10;

    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new Mutex();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    integer--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            mutex.lock();
            thread.start();
            System.out.println("线程" + i + "获得锁开始执行");
            mutex.unlock();
        }
        countDownLatch.countDown();
        Thread.sleep(3000);
        System.out.println("integer" + integer);
    }
}
