package org.queue;

import org.data.structure.Node;

/**
 * @author cuixiuyin@bonree.com
 * @date 2021/12/17 14:45
 * @desc
 */
public class NotifyFairnessBoundedBlockingQueue4 implements Queue {

    // 容量
    protected final int capacity;

    // 头指针，empty: head.next == tail == null
    protected Node head;

    // 尾指针
    protected Node tail;

    // guard: canPollCount, head, waitPollCount
    protected final Object pollLock = new Object();
    protected int canPollCount;
    protected int waitPollCount;

    // guard: canOfferCount, tail, waitOfferCount
    protected final Object offerLock = new Object();
    protected int canOfferCount;
    protected int waitOfferCount;

    public NotifyFairnessBoundedBlockingQueue4(int capacity) {
        this.capacity = capacity;
        this.canPollCount = 0;
        this.canOfferCount = capacity;
        this.waitPollCount = 0;
        this.waitOfferCount = 0;
        this.head = new Node(null);
        this.tail = head;
    }


    @Override
    public boolean offer(Object obj) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException(); // 线程已中断，直接退出即可，防止中断线程竞争锁
        }
        synchronized (offerLock) {
            while (canOfferCount <= 0) {
                waitOfferCount++;
                try {
                    offerLock.wait();
                } catch (InterruptedException e) {
                    // 触发其他线程
                    offerLock.notify();
                    throw e;

                } finally {
                    waitOfferCount--;
                }
            }
            Node node = new Node(obj);
            tail.next = node;
            tail = node;
            canOfferCount--;
        }
        synchronized (pollLock) {
            ++canPollCount;
            if (waitPollCount > 0) {
                pollLock.notify();
            }
        }
        return true;
    }


    @Override
    public Object poll() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object result = null;
        synchronized (pollLock) {
            while (canPollCount <= 0) {
                waitPollCount++;
                try {
                    pollLock.wait();
                } catch (InterruptedException e) {
                    pollLock.notify();
                    throw e;
                } finally {
                    waitPollCount--;
                }
            }

            result = head.next.value;
            head.next.value = 0;
            // ignore head;
            head = head.next;
            canPollCount--;
        }
        synchronized (offerLock) {
            canOfferCount++;
            if (waitOfferCount > 0) {
                offerLock.notify();
            }
        }
        return result;
    }
}
