package org.concurrency.demo.chapter6;

/**
 * @author : cuixiuyin
 * @date : 2019/8/28
 */
public class BitTest {

    // 1. >>> 无符号右移位，不管正数还是负数，高位都用0补齐（忽略符号位）
    // 2. 没有 <<< 这种运算符，因为左移都是补零，没有正负数的区别。
    // 3. << 有符号左移位，将运算数的二进制整体左移指定位数，低位用0补齐。
    // 4. >> 有符号右移位，将运算数的二进制整体右移指定位数，整数高位用0补齐，负数高位用1补齐（保持负数符号不变）

    public static void main(String[] args) {
        System.out.println(3 << 2);
        System.out.println(-3 << 2);
        System.out.println(16 >> 2);
        System.out.println(-16 >> 2);

        System.out.println(16 >>> 2);
        // 负数的二进制是正数二进制取反再 +1
        int i = -16 >>> 2;
        System.out.println(i);
    }
}
