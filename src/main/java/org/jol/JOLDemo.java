package org.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/7/1 13:40
 * @Description:
 */
public class JOLDemo {

    private static Object o = new Object();;

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("-----------加锁后的变化-------------------");
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
