package org.concurrency.demo.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Phaser;

/**
 * @author : cuixiuyin
 * @date : 2020/3/2
 * @Description 完成和 countDownLatch 一样的功能
 */
public class PhaserTest2 implements Runnable {

    private final Phaser phaser;

    public PhaserTest2(Phaser phaser) {
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

    public static void main(String[] args) throws IOException {
        Phaser phaser = new Phaser(1);
        for (int i = 0; i < 10; i++) {
            phaser.register();
            new Thread(new PhaserTest2(phaser), "Thread-" + i).start();
        }
        // 外部条件:等待用户输入命令
        System.out.println("Press ENTER to continue");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

        // 打开开关
        phaser.arriveAndDeregister();
        System.out.println("主线程打开了开关");
    }
}
