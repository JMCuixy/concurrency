package org.concurrency.demo.chapter6;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author : cuixiuyin
 * @date : 2019/9/2
 */
public class PriorityBlockingQueueTest {

    private static PriorityBlockingQueue<String> QUEUE = new PriorityBlockingQueue<>();

    public static void main(String[] args) {
        QUEUE.add("java");
        QUEUE.add("javaScript");
        QUEUE.add("c++");
        QUEUE.add("python");
        QUEUE.add("php");
        Iterator<String> it = QUEUE.iterator();
        while (it.hasNext()) {
            // c++  javaScript  java  python  php
            // 同优先级不保证顺序
            System.out.print(it.next() + "  ");
        }
    }
}
