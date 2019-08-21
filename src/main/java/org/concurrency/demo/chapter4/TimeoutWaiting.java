package org.concurrency.demo.chapter4;

/**
 * 利用 wait/notify 改造的超时等待模式
 *
 * @author : cuixiuyin
 * @date : 2019/8/21
 */
public class TimeoutWaiting {

    // 对当前对象加锁
    public synchronized Object get(long mills) throws InterruptedException {
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
        Object result = null;
        // 当超时大于0并且result返回值不满足要求
        // 当达到超时时间，直接退出，不会导致阻塞
        while ((result == null) && remaining > 0) {
            wait(remaining);
            remaining = future - System.currentTimeMillis();
        }
        return result;
    }
}
