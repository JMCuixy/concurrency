package org.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/11 15:37
 * @Description: 归并排序
 */
public class Test3 {


    /**
     * 归并排序的原理其实分治法。它首先将数组不断地二分，直到最后每个部分只包含 1 个数据。然后再对每个部分分别进行排序，最后将排序好
     * 的相邻的两部分合并在一起，这样整个数组就有序了。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 50};
        int[] tmp = new int[arr.length];
        System.out.println("原始数据: " + Arrays.toString(arr));
        customMergeSort(arr, tmp, 0, arr.length - 1);
        System.out.println("归并排序: " + Arrays.toString(arr));
    }


    public static void customMergeSort(int[] a, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            // 对左侧子序列进行递归排序
            customMergeSort(a, tmp, start, mid);
            // 对右侧子序列进行递归排序
            customMergeSort(a, tmp, mid + 1, end);
            // 合并
            customDoubleMerge(a, tmp, start, mid, end);
        }
    }


    public static void customDoubleMerge(int[] a, int[] tmp, int left, int mid, int right) {
        int p1 = left, p2 = mid + 1, k = left;
        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }
        while (p1 <= mid)
            tmp[k++] = a[p1++];
        while (p2 <= right)
            tmp[k++] = a[p2++];
        // 复制回原素组
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }
}
