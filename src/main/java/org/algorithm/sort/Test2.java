package org.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/11 13:43
 * @Description: 插入排序
 */
public class Test2 {

    /**
     * 选取未排序的元素，插入到已排序区间的合适位置，直到未排序区间为空。
     * <p>
     * 时间复杂度：最好的情况是 O(n)，最坏的情况是 O(n*n)；
     * <p>
     * 空间复杂度：O(1)
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 1, 23, 6, 78, 34};
        System.out.println("原始数据: " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
        insertSort();
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

    private static void insertSort() {
        int[] arr = {3, 2, 5, 1, 23, 6, 78, 34};
        System.out.println("原始数据: " + Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < temp) break;
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
    }

}
