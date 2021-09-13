package org.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/11 11:26
 * @Description: 冒泡排序
 */
public class Test1 {

    /**
     * 从第一个数据开始，依次比较相邻元素的大小。如果前者大于后者，则进行交换操作，把大的元素往后交换。通过多轮迭代，直到没有交换操作为止。
     * <p>
     * 时间复杂度：最好的情况 O(n)、最坏的情况 O(n^2);
     * <p>
     * 空间复杂度：O(1)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 0, 3, 4, 5, 10, -6, 7, 8, 9};
        System.out.println("原始数据: " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("冒泡排序: " + Arrays.toString(arr));
        bubbleSort();
    }


    /**
     * >
     * <p>
     * <p>
     * <p>
     * >
     * <p>
     * <p>
     * <p>
     * <p>
     * >
     */

    private static void bubbleSort() {
        int[] arr = {1, 0, 3, 4, 5, 10, -6, 7, 8, 9};
        System.out.println("原始数据: " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
    }
}
