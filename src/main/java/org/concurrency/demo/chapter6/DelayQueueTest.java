package org.concurrency.demo.chapter6;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : cuixiuyin
 * @date : 2019/9/3
 */
public class DelayQueueTest {

    private static DelayQueue<DelayElement> QUEUE = new DelayQueue<>();

    public static void main(String[] args) {
        // 1. 添加 10 个参数
        for (int i = 1; i < 10; i++) {
            // 2. 5 秒内随机延迟
            int nextInt = new Random().nextInt(5);
            long convert = TimeUnit.NANOSECONDS.convert(nextInt, TimeUnit.SECONDS);
            QUEUE.offer(new DelayElement(convert));
        }
        // 3. 查询元素排序 —— 延迟短的排在前面
        Iterator<DelayElement> iterator = QUEUE.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // 4. 可观察到元素延迟输出
        while (!QUEUE.isEmpty()) {
            Thread thread = new Thread(QUEUE.poll());
            thread.start();
        }
    }
}
