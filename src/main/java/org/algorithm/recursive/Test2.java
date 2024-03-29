package org.algorithm.recursive;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/10 18:17
 * @Description: >
 * 斐波那契数列是：0，1，1，2，3，5，8，13，21，34，55，89，144……。
 * <p>
 * 你会发现，这个数列中元素的性质是，某个数等于它前面两个数的和；也就是 a[n+2] = a[n+1] + a[n]。
 */
public class Test2 {

    /**
     * 现在的问题是，写一个函数，输入 x，输出斐波那契数列中第 x 位的元素。例如，输入 4，输出 2；输入 9，输出 21。要求：需要用递归的方式来实现。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(a(100));
    }

    private static Long a(Integer n) {
        if (n == 1) {
            return 0L;
        }
        if (n == 2) {
            return 1L;
        }
        return a(n - 1) + a(n - 2);
    }
}
