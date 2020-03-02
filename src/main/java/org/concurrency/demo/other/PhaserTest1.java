package org.concurrency.demo.other;

import java.util.concurrent.Phaser;

/**
 * @author : cuixiuyin
 * @date : 2020/3/2
 * @description 完成和 CylicBarrier 一样的功能
 */
public class PhaserTest1 implements Runnable {

    private final Phaser phaser;

    public PhaserTest1(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 开始执行任务，当前phase =" + phaser.getPhase() + "");
        // 1.等待其他参与者线程到达
        // 2.不响应中断，也就是说即使当前线程被中断，arriveAndAwaitAdvance方法也不会返回或抛出异常，而是继续等待。如果希望能够响应中断，可以参考 awaitAdvanceInterruptibly 方法。
        int advance = phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + ": 结束执行任务，当前phase =" + advance + "");
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++) {
            // 新增参与者数量
            phaser.register();
            new Thread(new PhaserTest1(phaser), "Thread-" + i).start();
        }
    }
}
