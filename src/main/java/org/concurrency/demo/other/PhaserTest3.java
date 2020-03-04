package org.concurrency.demo.other;

import java.io.IOException;
import java.util.concurrent.Phaser;

/**
 * @author : cuixiuyin
 * @date : 2020/3/3
 */
public class PhaserTest3 {

    /**
     * 每个Phaser对象对应的工作线程（任务）数
     */
    private static final int TASKS_PER_PHASER = 4;

    public static void main(String[] args) throws IOException {
        // 指定任务最多执行的次数
        int repeats = 1;
        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties + "] ---------------");
                return phase + 1 >= repeats || registeredParties == 0;
            }
        };

        Tasker[] taskers = new Tasker[10];
        // 根据任务数,为每个任务分配Phaser对象
        build(taskers, 0, taskers.length, phaser);
        // 执行任务
        for (int i = 0; i < taskers.length; i++) {
            Thread thread = new Thread(taskers[i]);
            thread.start();
        }
    }

    private static void build(Tasker[] taskers, int lo, int hi, Phaser phaser) {
        if (hi - lo > TASKS_PER_PHASER) {
            for (int i = lo; i < hi; i += TASKS_PER_PHASER) {
                int j = Math.min(i + TASKS_PER_PHASER, hi);
                build(taskers, i, j, new Phaser(phaser));
            }
        } else {
            for (int i = lo; i < hi; ++i) {
                taskers[i] = new Tasker(i, phaser);
            }
        }

    }
}

class Tasker implements Runnable {
    private final Phaser phaser;
    private int count;

    Tasker(Phaser phaser) {
        this.phaser = phaser;
        this.phaser.register();
    }

    Tasker(int i, Phaser phaser) {
        this.count = i;
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        //只要Phaser没有终止, 各个线程的任务就会一直执行
        while (!phaser.isTerminated()) {
            // 等待其它参与者线程到达
            int i = phaser.arriveAndAwaitAdvance();
            // do something
            System.out.println(Thread.currentThread().getName() + ": 执行完任务,count=" + count);
        }
    }
}
