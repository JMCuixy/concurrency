package org.concurrency.demo.chapter4.connectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : cuixiuyin
 * @date : 2019/8/21
 */
public class ConnectionPoolTest {

    private static ConnectionPool POOL = new ConnectionPool(10);
    /**
     * 保证所有 ConnectionRunner 能够同时开始
     */
    private static CountDownLatch START = new CountDownLatch(1);
    /**
     * main 线程将会等待所有 ConnectionRunner 结束后才能继续执行
     */
    private static CountDownLatch END;

    public static void main(String[] args) throws Exception {
        // 线程数量，可以修改线程数量进行观察
        int threadCount = 10;
        END = new CountDownLatch(threadCount);
        int count = 60;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        START.countDown();
        END.await();
        // 10 个线程，每个线程试着去获取 {count} 个连接
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                START.await();
            } catch (Exception ex) {

            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = POOL.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            // do something
                            connection.commit();
                        } finally {
                            POOL.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {

                } finally {
                    count--;
                }
            }
            END.countDown();
        }
    }
}
