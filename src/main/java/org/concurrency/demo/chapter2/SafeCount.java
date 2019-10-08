package org.concurrency.demo.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiuyin.cui
 * @Description
 * @date 2019/3/3 17:21
 */
public class SafeCount {

    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final SafeCount cas = new SafeCount();
        List<Thread> ts = new ArrayList<>(600);
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
    }

    /**
     * 使用CAS实现线程安全计数器
     */

    private void safeCount() {
        atomicI.getAndIncrement();
    }

    /**
     * 非线程安全计数器
     */

    private void count() {
        i++;
    }
}
