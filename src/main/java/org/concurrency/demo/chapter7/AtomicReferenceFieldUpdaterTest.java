package org.concurrency.demo.chapter7;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author : cuixiuyin
 * @date : 2019/9/7
 */
public class AtomicReferenceFieldUpdaterTest {

    // 创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicReferenceFieldUpdater<Stu, String> atomicUserFieldRef = AtomicReferenceFieldUpdater.newUpdater(Stu.class, String.class, "name");

    public static void main(String[] args) {
        Stu stu = new Stu(System.currentTimeMillis(), "张三");
        atomicUserFieldRef.set(stu, "李四");
        System.out.println(stu.getName());
    }
}
