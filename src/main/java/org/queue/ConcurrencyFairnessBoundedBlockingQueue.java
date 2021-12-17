package org.queue;

import org.data.structure.Node;

/**
 * @author cuixiuyin@bonree.com
 * @date 2021/12/17 11:30
 * @desc 非并发场景下，借助 ADT 实现一个基础版本
 */
public class ConcurrencyFairnessBoundedBlockingQueue implements Queue {

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

    public ConcurrencyFairnessBoundedBlockingQueue(int capacity) {
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
    public synchronized boolean offer(Object obj) {
        if (size < capacity) {
            Node node = new Node(obj);
            tail.next = node;
            tail = node;
            size++;
            return true;
        }
        return false;
    }


    /**
     * 无法实现阻塞等待，即如果队列为空，那么出队的动作还是会立即返回，返回为空；
     *
     * @return
     */
    @Override
    public synchronized Object poll() {
        // 出队时，本文通过迁移头结点的方式实现，避免修改尾节点
        if (head.next != null) {
            Object result = head.next.value;
            head.next.value = null;
            head = head.next;
            size--;
            return result;
        }
        return null;
    }
}
