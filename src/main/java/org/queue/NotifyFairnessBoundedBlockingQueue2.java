package org.queue;

import org.data.structure.Node;

/**
 * @author cuixiuyin@bonree.com
 * @date 2021/12/17 11:57
 * @desc ---
 * 1. 定义了两把锁， pollLock 和 offerLock 拆分出队和入队竞争；
 * 2. 出队的动作：首先拿到 pollLock 卫式等待后，完成出队动作；然后拿到 offerLock 发送通知，解除入队的等待线程。
 * 3. 入队的动作：首先拿到 offerLock 卫式等待后，完成入队的动作；然后拿到 pollLock 发送通知，解除出队的等待线程。
 */
public class NotifyFairnessBoundedBlockingQueue2 implements Queue {

    /**
     * 容量
     */
    protected final int capacity;
    /**
     * 头指针，empty: head.next == tail == null
     */
    protected Node head;
    /**
     * 尾指针
     */
    protected Node tail;
    /**
     * 入队数量和入队锁
     */
    protected final Object pollLock = new Object();
    protected int canPollCount;

    /**
     * 出队数量和出队锁
     */
    protected final Object offerLock = new Object();
    protected int canOfferCount;


    public NotifyFairnessBoundedBlockingQueue2(int capacity) {
        this.capacity = capacity;
        this.head = new Node(null);
        this.tail = head;
        this.canOfferCount = capacity;
        this.canPollCount = 0;
    }


    /**
     * 无法实现阻塞等待，即如果队列为满，那么入队的动作还是会立即返回，返回 false
     *
     * @param obj
     * @return
     */
    @Override
    public boolean offer(Object obj) throws InterruptedException {
        synchronized (offerLock) {
            while (canOfferCount  <= 0) {
                offerLock.wait();
            }
            Node node = new Node(obj);
            tail.next = node;
            tail = node;
            canOfferCount--;
        }
        synchronized (pollLock) {
            ++canPollCount;
            pollLock.notifyAll();
        }
        return true;
    }


    /**
     * 无法实现阻塞等待，即如果队列为空，那么出队的动作还是会立即返回，返回为空；
     *
     * @return
     */
    @Override
    public synchronized Object poll() throws InterruptedException {
        Object result = null;
        synchronized (pollLock) {
            while (canPollCount <= 0) {
                pollLock.wait();
            }
            // 出队时，本文通过迁移头结点的方式实现，避免修改尾节点
            result = head.next.value;
            head.next.value = null;
            head = head.next;
            canPollCount--;
        }
        synchronized (offerLock) {
            canOfferCount++;
            offerLock.notifyAll();
        }
        return result;
    }
}
