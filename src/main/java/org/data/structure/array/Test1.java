package org.data.structure.array;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/8 15:27
 * @Description: 例题，假设数组存储了 5 个评委对 1 个运动员的打分，且每个评委的打分都不相等。现在需要你：
 * <p>
 * 1. 用数组按照连续顺序保存，去掉一个最高分和一个最低分后的 3 个打分样本；
 * 2. 计算这 3 个样本的平均分并打印。
 * <p>
 * 要求是，不允许再开辟 O(n) 空间复杂度的复杂数据结构。
 */
public class Test1 {

    public static void main(String[] args) {

        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        Integer min = array[0];
        Integer minIndex = 0;
        Integer max = array[0];
        Integer maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            Integer val = array[i];
            if (val > max) {
                max = val;
                maxIndex = i;
            }
            if (val < min) {
                min = val;
                minIndex = i;
            }
        }
        Integer sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (minIndex != i && maxIndex != i) {
                sum += array[i];
            }
        }
        double v = sum / 3.0;
        System.out.println(v);
    }
}
