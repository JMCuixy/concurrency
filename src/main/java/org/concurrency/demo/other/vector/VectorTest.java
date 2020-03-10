package org.concurrency.demo.other.vector;

import java.util.Vector;

/**
 * @author : cuixiuyin
 * @date : 2020/3/10
 */
public class VectorTest {

    static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            Thread printThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    System.out.println(vector.get(i));
                }
            });
            removeThread.start();
            printThread.start();
            // 不要同时产生过多线程，否则会导致操作系统假死
            while (Thread.activeCount() > 20) break;
        }
    }
}
