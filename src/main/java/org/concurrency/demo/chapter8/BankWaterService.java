package org.concurrency.demo.chapter8;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : cuixiuyin
 * @date : 2019/9/10
 * 银行流水处理服务类
 */
public class BankWaterService implements Runnable {

    // 创建4个屏障，处理完之后执行当前类的run方法
    private CyclicBarrier barrier = new CyclicBarrier(4, this);
    // 假设有4个计算任务，所以只启动4个线程
    private Executor executor = Executors.newFixedThreadPool(4);
    // 保存每个任务的计算结果
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    private void count() {
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() -> {
                // 当前任务的计算结果，计算过程忽略
                sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                // 计算完成，插入一个屏障
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, "线程" + atomicInteger.getAndIncrement());
            executor.execute(thread);
        }
    }

    @Override
    public void run() {
        int result = 0;
        // 汇总每个任务计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        //将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();
    }
}
