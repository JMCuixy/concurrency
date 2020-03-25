package org.concurrency.demo.other;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author : cuixiuyin
 * @date : 2020/3/17
 */
public class ConcurrentSkipListMapTest {

    //private static Map<String, String> MAP = new TreeMap<String, String>();
    private static Map<String, String> MAP = new ConcurrentSkipListMap<String, String>();

    public static void main(String[] args) {
        // 同时启动两个线程对map进行操作！
        new MyThread("A").start();
        new MyThread("B").start();
    }

    private static void printAll() {
        String key, value;
        Iterator iterator = MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            key = (String) entry.getKey();
            value = (String) entry.getValue();
            System.out.print("(" + key + ", " + value + "), ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // "线程名" + "序号"
                String val = Thread.currentThread().getName() + i;
                MAP.put(val, "0");
                printAll();
            }
        }
    }
}
