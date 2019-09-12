package org.concurrency.demo.chapter8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : cuixiuyin
 * @date : 2019/9/11
 */
public class ExchangerTest {

    // 1. Exchanger（交换者）是一个用于线程间协作的工具类 —— 用于线程间的数据交换。它提供一个同步点，在这个同步点，两个线程可以交换彼此的数据。
    // 2. 这两个线程通过 exchange 方法交换数据，如果第一个线程先执行 exchange() 方法，它会一直等待第二个线程也执行 exchange 方法。
    // 3. 遗传算法：遗传算法里需要选出两个人作为交配对象，这时候会交换两人的数据，并使用交叉规则得出2个交配结果。
    // 4. Exchanger 也可以用于校对工作，比如我们需要将纸制银行流水通过人工的方式录入成电子银行流水，为了避免错误，采用AB岗两人进行录入，录入到Excel之后，系统需要加载这两个Excel，并对两个Excel数据进行校对

    private static final Exchanger<String> exchange = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                exchange.exchange("校验结果A");
            } catch (InterruptedException e) {
            }

        });
        threadPool.execute(() -> {
            try {
                String resultB = "校验结果B";
                String resultA = exchange.exchange("");
                System.out.println("A 和 B 校验结果是否一致：" + resultA.equals(resultB) + "，A 录入的是：" + resultA + "，" +
                        "B 录入是：" + resultB);
            } catch (InterruptedException e) {
            }
        });
        threadPool.shutdown();
    }
}
