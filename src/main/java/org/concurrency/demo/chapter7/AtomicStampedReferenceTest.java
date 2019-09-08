package org.concurrency.demo.chapter7;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : cuixiuyin
 * @date : 2019/9/7
 */
public class AtomicStampedReferenceTest {

    private static Stu stu = new Stu(System.currentTimeMillis(), "张三");
    /**
     * 更新对象的时候带一个版本号，可以防止 CAS 中 ABA 问题。原理在于 compare 的时候不仅比较原来的值，还比较版本号。同理更新的时候也需要更新版本号
     */
    private static AtomicStampedReference<Stu> stampedReference = new AtomicStampedReference(stu, 1);

    public static void main(String[] args) {
        System.out.println(stampedReference.getReference());
        Stu newStu = new Stu(System.currentTimeMillis(), "李四");
        int stamp = stampedReference.getStamp();
        stampedReference.compareAndSet(stu, newStu, stamp, stamp++);
        System.out.println(stampedReference.getReference());
    }
}
