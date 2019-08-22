package org.concurrency.demo.chapter4.threadPool;

/**
 * @author : cuixiuyin
 * @date : 2019/8/21
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        // 1、新建一个线程池
        ThreadPool threadPool = new DefaultThreadPool();
        threadPool.addWorkers(3);
        threadPool.execute(() -> System.out.println("Hello Job"));
        threadPool.shutdown();
    }
}
