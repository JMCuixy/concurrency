package org.concurrency.demo.chapter7;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author : cuixiuyin
 * @date : 2019/9/7
 */
public class AtomicReferenceArrayTest {

    // AtomicReferenceArray 会将当前数组（VALUE）复制一份，所以当 AtomicReferenceArray 对内部的数组元素进行修改时，不会影响传入的数组。
    private static Stu[] VALUE = new Stu[]{new Stu(System.currentTimeMillis(), "张三"),
            new Stu(System.currentTimeMillis(), "李四")};

    private static AtomicReferenceArray<Stu> REFERENCE_ARRAY = new AtomicReferenceArray<>(VALUE);

    public static void main(String[] args) {
        // 修改指定位置元素的值
        REFERENCE_ARRAY.getAndSet(0, new Stu(System.currentTimeMillis(), "王五"));
        System.out.println(REFERENCE_ARRAY.get(0));
        System.out.println(VALUE[0]);
    }
}
