package org.concurrency.demo.chapter6;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author : cuixiuyin
 * @date : 2019/9/3
 */
public class SynchronousQueueTest {

    private static class SynchronousQueueProducer implements Runnable {

        private BlockingQueue<String> blockingQueue;

        private SynchronousQueueProducer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    System.out.println(Thread.currentThread().getName() + " Put: " + data);
                    blockingQueue.put(data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static class SynchronousQueueConsumer implements Runnable {

        private BlockingQueue<String> blockingQueue;

        private SynchronousQueueConsumer(BlockingQueue<String> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " take(): " + blockingQueue.take());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {

        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        SynchronousQueueProducer queueProducer = new SynchronousQueueProducer(synchronousQueue);
        new Thread(queueProducer, "producer - 1").start();
        SynchronousQueueConsumer queueConsumer1 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer1, "consumer — 1").start();
        SynchronousQueueConsumer queueConsumer2 = new SynchronousQueueConsumer(synchronousQueue);
        new Thread(queueConsumer2, "consumer — 2").start();
    }
}
