package org.concurrency.demo.chapter7;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author : cuixiuyin
 * @date : 2019/9/8
 */
public class LongAdderTest {


    private static LongAdder longAdder = new LongAdder();
    private static LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public static void main(String[] args) {
        longAdder.increment();
        System.out.println(longAdder.sum());

        longAccumulator.accumulate(2);
        System.out.println(longAccumulator.get());
    }
}
