package org.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/11 17:37
 * @Description: 快速排序
 */
public class Test4 {


    /**
     * 快速排序法的原理也是分治法。它的每轮迭代，会选取数组中任意一个数据作为分区点，将小于它的元素放在它的左侧，大于它的放在它的右侧。
     * 再利用分治思想，继续分别对左右两侧进行同样的操作，直至每个区间缩小为 1，则完成排序。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 11, 4, 5, 10, 8};
        System.out.println("原始数据: " + Arrays.toString(arr));
        customQuickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序: " + Arrays.toString(arr));
    }

    public static void customQuickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low >= high) {
            return;
        }
        i = low;
        j = high;
        temp = arr[low];
        while (i < j) {
            // 先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            // 再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
        }
        arr[low] = arr[i];
        arr[i] = temp;
        // 递归调用左半数组
        customQuickSort(arr, low, j - 1);
        // 递归调用右半数组
        customQuickSort(arr, j + 1, high);
    }


}
