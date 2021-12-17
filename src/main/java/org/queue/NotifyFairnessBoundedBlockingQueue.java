package org.queue;

import org.data.structure.Node;

/**
 * @author cuixiuyin@bonree.com
 * @date 2021/12/17 11:57
 * @desc  ---
 * 1. 入队和出队动作阻塞等待同一把锁，恶性竞争；
 * 2. 队里变更需要 notifyAll 避免线程中断或异常，丢失消息；
 */
public class NotifyFairnessBoundedBlockingQueue implements Queue {

    /**
     * 当前大小
     */
    protected int size;
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

    public NotifyFairnessBoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.head = new Node(null);
        this.tail = head;
        this.size = 0;
    }


    /**
     * 无法实现阻塞等待，即如果队列为满，那么入队的动作还是会立即返回，返回 false
     *
     * @param obj
     * @return
     */
    @Override
    public synchronized boolean offer(Object obj) throws InterruptedException {
        while (size >= capacity) {
            wait();
        }
        Node node = new Node(obj);
        tail.next = node;
        tail = node;
        size++;
        // 唤醒出队
        notifyAll();
        return true;
    }


    /**
     * 无法实现阻塞等待，即如果队列为空，那么出队的动作还是会立即返回，返回为空；
     *
     * @return
     */
    @Override
    public synchronized Object poll() throws InterruptedException {
        while (head.next == null) {
            wait();
        }

        // 出队时，本文通过迁移头结点的方式实现，避免修改尾节点
        Object result = head.next.value;
        head.next.value = null;
        head = head.next;
        size--;
        return result;
    }
}
