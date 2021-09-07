package org.data.structure.linkedList;

import org.data.structure.Node;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/3 19:54
 * @Description: 例 1，链表的翻转。给定一个链表，输出翻转后的链表。例如，输入1 ->2 -> 3 -> 4 ->5，输出 5 -> 4 -> 3 -> 2 -> 1。
 */
public class Test1 {


    /**
     * 1. 如果是数组的翻转，这会非常容易。原因在于，数组在连续的空间进行存储，可以直接求解出数组的长度。而且，数组可以通过索引值去查
     * 找元素，然后对相应的数据进行交换操作而完成翻转。
     * <p>
     * 2. 但对于某个单向链表，它的指针结构造成了它的数据通路有去无回，一旦修改了某个指针，后面的数据就会造成失联的状态。为了解决这个
     * 问题，我们需要构造三个指针 prev、curr 和 next，对当前结点、以及它之前和之后的结点进行缓存，再完成翻转动作。
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
        // 构造三个指针 prev、curr 和 next
        Node prev = null;
        Node curr = node1;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        System.out.println(node5.toString());
    }
}
