package org.data.structure.array;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/8 15:57
 * @Description: >
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后的数组和新的长度，你不需要考虑数组中超出新长度
 * 后面的元素。要求，空间复杂度为 O(1)，即不要使用额外的数组空间。
 * <p>
 * 例如，给定数组 nums = [1,1,2]，函数应该返回新的长度 2，并且原数组 nums 的前两个元素被修改为 1, 2。 又如，给定 nums = [0,0,1,1,
 * 1,2,2,3,3,4]，函数应该返回新的长度 5，并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 */
public class Test2 {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int temp = array[0];
        int len = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != temp) {
                array[len] = array[i];
                temp = array[i];
                len ++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }

        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(array[i]);
        }
    }
}
