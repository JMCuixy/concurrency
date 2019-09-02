package org.concurrency.demo.chapter6;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 无界非阻塞队列
 *
 * @author : cuixiuyin
 * @date : 2019/8/30
 */
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();


    public static void main(String[] args) {
        concurrentLinkedQueue.add(1);
        concurrentLinkedQueue.add(2);
        concurrentLinkedQueue.add(3);
        System.out.println(concurrentLinkedQueue.poll());
    }
}
