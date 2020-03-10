package org.concurrency.demo.other.vector;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : cuixiuyin
 * @date : 2020/3/10
 */
public class ListTest {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);

        Set<String> set = new HashSet<>();
        Set<String> synchronizedSet = Collections.synchronizedSet(set);

    }


    public void lockTest() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
