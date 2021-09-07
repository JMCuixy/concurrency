package org.data.structure.linkedList;

import org.data.structure.Node;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/6 17:16
 * @Description: 例 2，给定一个奇数个元素的链表，查找出这个链表中间位置的结点的数值。
 */
public class Test2 {

    /**
     * 1. 一个暴力的办法是，先通过一次遍历去计算链表的长度，这样我们就知道了链表中间位置是第几个。接着再通过一次遍历去查找这个位置的数值。
     * <p>
     * 2. 还有一个巧妙的办法，就是利用快慢指针进行处理。其中快指针每次循环向后跳转两次，而慢指针每次向后跳转一次。如下图所示。
     *
     * @param args
     */
    public static void main(String[] args) {
        // 构造单向链表
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        System.out.println(node1.toString());
        Node slow = node1;
        Node fast = node1;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println(slow.toString());

    }
}
