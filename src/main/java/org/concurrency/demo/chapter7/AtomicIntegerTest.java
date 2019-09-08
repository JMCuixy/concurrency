package org.concurrency.demo.chapter7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : cuixiuyin
 * @date : 2019/9/6
 */
public class AtomicIntegerTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                    // 以原子方式将当前值加 1，并返回之前的值
                    System.out.print(atomicInteger.getAndIncrement() + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        // 线程同时进行争抢操作
        countDownLatch.countDown();
        Thread.sleep(2000);
        System.out.println();
        // 以原子方式将输入的数值与实例中的值相加，并返回结果。
        System.out.println(atomicInteger.addAndGet(10));
        // CAS 操作
        atomicInteger.compareAndSet(21, 30);
        System.out.println(atomicInteger.get());
    }
}
