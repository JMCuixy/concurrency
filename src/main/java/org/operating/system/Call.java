package org.operating.system;

/**
 * @author cuixiuyin@bonree.com
 * @date 2021/12/27 10:25
 * @desc 怎么用非递归算法实现斐波那契数列
 */
public class Call {

    public static void main(String[] args) {
        int fib = fib(4);
        System.out.println(fib);
        int fib2 = fib2(4);
        System.out.println(fib2);
    }

    static int fib(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    private static int fib2(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        //初始化数据
        int[] stack = new int[n];
        int point = n - 3;
        stack[n - 1] = 1;
        stack[n - 2] = 2;
        //数组模拟出栈入栈
        while (point >= 0) {
            stack[point] = stack[point + 1] + stack[point + 2];
            point--;
        }
        return stack[0];
    }
}
