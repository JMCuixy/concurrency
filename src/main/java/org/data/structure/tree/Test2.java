package org.data.structure.tree;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/9 15:45
 * @Description: 前序遍历
 */
public class Test2 {

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
        preOrderTraverse(n16);
        System.out.println();
        inOrderTraverse(n16);
        System.out.println();
        postOrderTraverse(n16);
    }

    // 先序遍历
    public static void preOrderTraverse(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    // 中序遍历
    public static void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.leftChild);
        System.out.print(node.data + " ");
        inOrderTraverse(node.rightChild);
    }

    // 后序遍历
    public static void postOrderTraverse(Node node) {
        if (node == null)
            return;
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data + " ");
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
