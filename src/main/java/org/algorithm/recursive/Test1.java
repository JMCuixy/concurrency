package org.algorithm.recursive;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/10 17:59
 * @Description: >
 * 汉诺塔问题是源于印度一个古老传说的益智玩具。大梵天创造世界的时候做了三根金刚石柱子，在一根柱子上从下往上按照大小顺
 * 序摞着 64 片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上，并且规定，在小圆盘上不能放大圆盘，在三根
 * 柱子之间一次只能移动一个圆盘。
 */
public class Test1 {

    public static void main(String[] args) {
        String a = "A";
        String b = "B";
        String c = "C";
        hanio(10, a, b, c);
    }

    public static void hanio(int n, String a, String b, String c) {
        if (n < 1) {
            System.out.println("汉诺塔的层数不能小于1");
        } else if (n == 1) {
            System.out.println("移动: " + a + " -> " + c);
        } else {
            hanio(n - 1, a, c, b);
            System.out.println("移动: " + a + " -> " + c);
            hanio(n - 1, b, a, c);
        }
    }
}
