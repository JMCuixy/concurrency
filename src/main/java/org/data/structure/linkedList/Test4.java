package org.data.structure.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/6 18:15
 * @Description: 练习题：给定一个含有 n 个元素的链表，现在要求每 k 个节点一组进行翻转，打印翻转后的链表结果。其中，k 是一个正整数，且可被 n 整除。
 * <p>
 * 例如，链表为 1 -> 2 -> 3 -> 4 -> 5 -> 6，k = 3，则打印 321654。我们给出一些提示，这个问题需要使用到链表翻转的算法。
 */
public class Test4 {


    public static void main(String[] args) {
        // 构造单向链表
        LinkedList<Integer> list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);


        System.out.println(list.toString());
        int k = 3;
        LinkedList<Integer> newList = new LinkedList();
        // 构造栈
        Stack<Integer> stack = new Stack<>();

        Iterator<Integer> it = list.iterator();
        for (int i = 1; i < list.size() + 1; i++) {
            Integer next = it.next();
            stack.push(next);
            if (i % k == 0) {
                while (!stack.empty()) {
                    newList.add(stack.pop());
                }
            }
        }
        System.out.println(newList.toString());
    }
}
