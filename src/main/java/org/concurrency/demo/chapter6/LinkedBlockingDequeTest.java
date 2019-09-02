package org.concurrency.demo.chapter6;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author : cuixiuyin
 * @date : 2019/9/2
 */
public class LinkedBlockingDequeTest {

    private static LinkedBlockingDeque<String> DEQUE = new LinkedBlockingDeque<>(2);

    public static void main(String[] args) {
        DEQUE.addFirst("java");
        DEQUE.addFirst("c++");
        // java
        System.out.println(DEQUE.peekLast());
        // java
        System.out.println(DEQUE.pollLast());
        DEQUE.addLast("php");
        // c++
        System.out.println(DEQUE.pollFirst());
    }
}
