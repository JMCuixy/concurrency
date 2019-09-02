package org.concurrency.demo.chapter6;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author : cuixiuyin
 * @date : 2019/9/1
 */
public class ArrayBlockingQueueTest {

    /**
     * 1. 由于是有界阻塞队列，需要设置初始大小
     * 2. 默认不保证阻塞线程的公平访问，可设置公平性
     */
    private static ArrayBlockingQueue<String> QUEUE = new ArrayBlockingQueue<>(2, true);

    public static void main(String[] args) throws InterruptedException {

        Thread put = new Thread(() -> {
            // 3. 尝试插入元素
            try {
                QUEUE.put("java");
                QUEUE.put("javaScript");
                // 4. 元素已满，会阻塞线程
                QUEUE.put("c++");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        put.start();
        Thread take = new Thread(() -> {
            try {
                // 5. 获取一个元素
                System.out.println(QUEUE.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        take.start();
        // 6 javaScript、c++
        System.out.println(QUEUE.take());
        System.out.println(QUEUE.take());
    }
}
