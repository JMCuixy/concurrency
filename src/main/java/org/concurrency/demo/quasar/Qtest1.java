package org.concurrency.demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;

/**
 * @author : cuixiuyin
 * @date : 2020/3/25
 */
public class Qtest1 {

    public static void main(String[] args) {
        new Fiber<Void>() {
            @Override
            protected Void run() throws SuspendExecution, InterruptedException {
                System.out.println("Hello Fiber");
                return null;
            }
        }.start();

        new Fiber<>(() -> {
            System.out.println("Hello Fiber");
            return null;
        }).start();
    }
}
