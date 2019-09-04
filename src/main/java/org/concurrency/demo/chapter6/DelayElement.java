package org.concurrency.demo.chapter6;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author : cuixiuyin
 * @date : 2019/9/3
 */
public class DelayElement implements Delayed, Runnable {

    private static final AtomicLong SEQUENCER = new AtomicLong();
    /**
     * 标识元素先后顺序
     */
    private final long sequenceNumber;
    /**
     * 延迟时间，单位纳秒
     */
    private long time;

    public DelayElement(long time) {
        this.time = System.nanoTime() + time;
        this.sequenceNumber = SEQUENCER.getAndIncrement();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        // compare zero if same object
        if (other == this) {
            return 0;
        }
        if (other instanceof DelayElement) {
            DelayElement x = (DelayElement) other;
            long diff = time - x.time;
            if (diff < 0) {
                return -1;
            } else if (diff > 0) {
                return 1;
            } else if (sequenceNumber < x.sequenceNumber) {
                return -1;
            } else {
                return 1;
            }
        }
        long diff = getDelay(NANOSECONDS) - other.getDelay(NANOSECONDS);
        return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
    }

    @Override
    public void run() {
        System.out.println("sequenceNumber" + sequenceNumber);
    }

    @Override
    public String toString() {
        return "DelayElement{" + "sequenceNumber=" + sequenceNumber + ", time=" + time + '}';
    }
}
