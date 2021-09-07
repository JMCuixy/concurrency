package org.data.structure.linkedList;

import org.data.structure.Node;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/6 17:31
 * @Description: 例 3，判断链表是否有环。如下图所示，这就是一个有环的链表。
 */
public class Test3 {

    /**
     * 链表的快慢指针方法，在很多链表操作的场景下都非常适用，对于这个问题也是一样。
     * <p>
     * 假设链表有环，这个环里面就像是一个跑步赛道的操场一样。经过多次循环之后，快指针和慢指针都会进入到这个赛道中，就好像两个跑步选手在比赛。快指针每次走两格，而慢指针每次走一格，相对而言，快指针每次循环会多走一步。这就意味着：
     * <p>
     * - 如果链表存在环，快指针和慢指针一定会在环内相遇，即 fast == slow 的情况一定会发生。
     * - 反之，则最终会完成循环，二者从未相遇。
     *
     * @param args
     */
    public static void main(String[] args) {
        // 构造环向链表
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node3);

        Node fast = node1;
        Node slow = node1;

        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        System.out.println(Boolean.TRUE);
    }
}
