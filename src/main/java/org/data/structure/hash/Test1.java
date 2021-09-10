package org.data.structure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/10 15:53
 * @Description: >
 * 给定一个整数数组 arr 和一个目标值 target，请你在该数组中找出加和等于目标值的那两个整数，并返回它们的在数组中下标。
 */
public class Test1 {


    /**
     * 假设原数组中没有重复元素，而且有且只有一组答案。但是，数组中的元素只能使用一次。
     * <p>
     * 例如，arr = [1, 2, 3, 4, 5, 6]，target = 4。因为，arr[0] + arr[2] = 1 + 3 = 4 = target，则输出 0，2。
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer target = 4;
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6};
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            int e = target - arr[i];
            if (map.containsKey(e) && map.get(e) != i) {
                System.out.println(i + "->" + arr[i]);
                System.out.println(map.get(e) + "->" + e);
            }
        }

    }
}
