package org.concurrency.demo.chapter8;

import java.util.concurrent.CyclicBarrier;

/**
 * @author : cuixiuyin
 * @date : 2019/9/9
 */
public class CyclicBarrierTest {

    // 1. CyclicBarrier 设置一个屏障（也可以叫同步点），拦截阻塞一组线程，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
    // 2. CyclicBarrier 默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数量，每个线程调用await方法告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。
    // 3. CyclicBarrier 还提供一个更高级的构造函数 CyclicBarrier（int parties，Runnable barrierAction），用于在线程到达屏障时，优先执行 barrierAction，方便处理更复杂的业务场景.
    // 4. CyclicBarrier 可以用于多线程计算数据，最后合并计算结果的场景
    // 5. CountDownLatch 的计数器只能使用一次，而 CyclicBarrier 的计数器可以使用 reset() 方法重置。所以 CyclicBarrier 能处理更为复杂的业务场景。例如，如果计算发生错误，可以重置计数器，并让线程重新执行一次。
    // 7. getNumberWaiting 方法可以获得CyclicBarrier。阻塞的线程数量。isBroken()方法用来了解阻塞的线程是否被中断

    private static final CyclicBarrier BARRIER = new CyclicBarrier(2);
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(2, () -> System.out.println(3));

    public static void main(String[] args) {
        // 对应上面的说明 2
        // test1();
        // 对应上面的说明 3
        // test2();
        // BankWaterService.java 对应上面的说明 4
        // test3 对应上面的说明 7
        test3();

    }

    private static void test1() {
        new Thread(() -> {
            try {
                BARRIER.await();
            } catch (Exception e) {
            }
            System.out.println(1);
        }
        ).start();
        try {
            BARRIER.await();
        } catch (Exception e) {
        }
        System.out.println(2);
    }

    private static void test2() {
        new Thread(() -> {
            try {
                CYCLIC_BARRIER.await();
            } catch (Exception e) {
            }
            System.out.println(1);

        }).start();
        try {
            CYCLIC_BARRIER.await();
        } catch (Exception e) {
        }
        System.out.println(2);

    }

    private static void test3() {
        Thread thread = new Thread(() -> {
            try {
                BARRIER.await();
            } catch (Exception e) {
            }

        });
        thread.start();
        thread.interrupt();
        try {
            BARRIER.await();
        } catch (Exception e) {
            System.out.println(BARRIER.isBroken());
        }
    }
}
