package org.concurrency.demo.other;

/**
 * final与可见性
 *
 * @author : cuixiuyin
 * @date : 2020/3/4
 */
public class FinalTest {

    private static final int i;

    private final int j;

    static {
        i = 0;
    }

    {
        // 也可以选择在构造函数中初始化
        j = 0;
    }
}
