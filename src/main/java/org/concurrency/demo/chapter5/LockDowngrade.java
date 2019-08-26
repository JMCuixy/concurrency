package org.concurrency.demo.chapter5;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级是指把持住（当前拥有的）写锁，再获取到读锁，随后释放（先前拥有的）写锁的过程。
 * 简单来说就是同一个线程中，写锁处理数据的同时，对读锁获取（防止其他写线程争抢到锁，从而改变数据）
 *
 * @author : cuixiuyin
 * @date : 2019/8/26
 */
public class LockDowngrade {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private volatile Boolean update = false;

    public void processData() {
        readLock.lock();
        if (!update) {
            // 必须先释放读锁
            readLock.unlock();
            // 锁降级从写锁获取到开始
            writeLock.lock();
            try {
                if (!update) {
                    // 准备数据的流程（略）
                    update = true;
                }
                readLock.lock();
            } finally {
                writeLock.unlock();
            }
            //锁降级完成，写锁降级为读锁
        }
        try {
            // 使用数据的流程（略）
        } finally {
            readLock.unlock();
        }
    }
}
