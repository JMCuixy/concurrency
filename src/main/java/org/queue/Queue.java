package org.queue;

/**
 * @author cuixiuyin@bonree.com
 * @date 2021/12/17 11:26
 * @desc 借助原生的 JAVA 同步原语，实现一个公平有界的阻塞队列
 */
public interface Queue {

    /**
     * 队列添加元素
     *
     * @param obj
     * @return
     */
    boolean offer(Object obj) throws InterruptedException;

    /**
     * 队列移除元素
     *
     * @return
     */
    Object poll() throws InterruptedException;
}
