package org.concurrency.demo.chapter6;

import java.util.HashMap;
import java.util.UUID;

/**
 * HashMap 在并发执行 put操作时会引起死循环，是因为多线程会导致 HashMap 的 Entry 链表形成环形数据结构，一旦形成环形数据结构，
 * Entry的next节点永远不为空，就会产生死循环获取Entry
 *
 * @author : cuixiuyin
 * @date : 2019/8/27
 */
public class hashMapTest {

    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(2);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");
        thread.start();
        thread.join();
    }
}
