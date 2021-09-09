package org.data.structure.tree;

import java.util.LinkedList;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/9 15:45
 * @Description: 。给定一棵树，按照层次顺序遍历并打印这棵树。注意不是前序遍历
 */
public class Test1 {

    /**
     *     16
     *  /     \
     * 13     20
     * /\      \
     *10 15     22
     *          / \
     *          21 26
     * @param args
     */
    public static void main(String[] args) {

        Node n10 = new Node(10, null, null);
        Node n15 = new Node(15, null, null);
        Node n21 = new Node(21, null, null);
        Node n26 = new Node(26, null, null);

        Node n13 = new Node(13, n10, n15);
        Node n22 = new Node(22, n21, n26);
        Node n20 = new Node(20, null, n22);
        Node n16 = new Node(16, n13, n20);
        levelTraverse(n16);

    }

    public static void levelTraverse(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = null;
        // 根节点入队
        queue.offer(root);
        // 只要队列中有元素，就可以一直执行，非常巧妙地利用了队列的特性
        while (!queue.isEmpty()) {
            // 出队队头元素
            current = queue.poll();
            System.out.print("-->" + current.data);
            // 左子树不为空，入队
            if (current.leftChild != null)
                queue.offer(current.leftChild);
            // 右子树不为空，入队
            if (current.rightChild != null)
                queue.offer(current.rightChild);
        }
    }

    private static class Node {

        Integer data;

        Node leftChild;

        Node rightChild;

        public Node(Integer data, Node leftChild, Node rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }


}
