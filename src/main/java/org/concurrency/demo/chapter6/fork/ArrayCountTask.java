package org.concurrency.demo.chapter6.fork;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author : cuixiuyin
 * @date : 2019/9/5
 */
public class ArrayCountTask extends RecursiveTask<Long> {
    /**
     * 阈值
     */
    private static final Integer THRESHOLD = 10000;

    private Integer[] array;
    private Integer start;
    private Integer end;

    public ArrayCountTask(Integer[] array, Integer start, Integer end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        // 最小子任务计算
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
        } else {
            // 把大于阈值的任务继续往下拆分，有点类似递归的思维。 recursive 就是递归的意思。
            int middle = (start + end) >>> 1;
            ArrayCountTask leftArrayCountTask = new ArrayCountTask(array, start, middle);
            ArrayCountTask rightArrayCountTask = new ArrayCountTask(array, middle, end);
            // 执行子任务
            //leftArrayCountTask.fork();
            //rightArrayCountTask.fork();

            // invokeAll 方法使用
            invokeAll(leftArrayCountTask, rightArrayCountTask);

            //等待子任务执行完，并得到其结果
            Long leftJoin = leftArrayCountTask.join();
            Long rightJoin = rightArrayCountTask.join();
            // 合并子任务的结果
            sum = leftJoin + rightJoin;
        }

        return sum;
    }

    public static void main(String[] args) {
        // 1. 造一个 int 类型的百万级别数组
        Integer[] array = new Integer[150000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        // 2.普通方式计算结果
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("普通方式计算结果：" + sum + "，耗时：" + (end - start));
        long start2 = System.currentTimeMillis();
        // 3.fork/join　框架方式计算结果
        ArrayCountTask arrayCountTask = new ArrayCountTask(array, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        sum = forkJoinPool.invoke(arrayCountTask);
        // 检查任务是否已经抛出异常或已经被取消了
        if (arrayCountTask.isCompletedAbnormally()) {
            // 1. getException 方法返回 Throwable 对象。
            // 2. 如果任务被取消了则返回CancellationException。
            // 3. 如果任务没有完成或者没有抛出异常则返回null。
            System.out.println(arrayCountTask.getException());
        }
        long end2 = System.currentTimeMillis();
        System.out.println("fork/join　框架方式计算结果：" + sum + "，耗时：" + (end2 - start2));

        // 结论：
        // 1. 电脑 i5-4300m，双核四线程
        // 2. 数组量少的时候，fork/join 框架要进行线程创建/切换的操作，性能不明显。
        // 3. 数组量超过 100000000，fork/join 框架的性能才开始体现。

    }
}
