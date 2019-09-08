package org.concurrency.demo.chapter7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author : cuixiuyin
 * @date : 2019/9/6
 */
public class AtomicArrayTest {

    // AtomicIntegerArray会将当前数组（VALUE）复制一份，所以当 AtomicIntegerArray 对内部的数组元素进行修改时，不会影响传入的数组。
    private static int[] VALUE = new int[]{1, 2};
    private static AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(VALUE);

    public static void main(String[] args) {
        // 修改指定位置元素的值
        ATOMIC_INTEGER_ARRAY.getAndSet(0, 3);
        System.out.println(ATOMIC_INTEGER_ARRAY.get(0));
        System.out.println(VALUE[0]);
    }
}
