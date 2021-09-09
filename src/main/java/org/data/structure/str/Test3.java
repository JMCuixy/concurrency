package org.data.structure.str;

import java.util.Stack;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/9 14:13
 * @Description: >
 * <p>
 * 给定一个字符串，逐个翻转字符串中的每个单词。例如，输入: "the sky is blue"，输出: "blue is sky the"。
 */
public class Test3 {


    public static void main(String[] args) {
        String str = "the sky is blue";
        String str2 = "";
        Stack stack = new Stack();
        for (int i = str.length() - 1; i >= 0; i--) {
            char charAt = str.charAt(i);
            if (' ' != charAt) {
                stack.push(charAt);
            }
            if (' ' == charAt || i == 0) {
                while (!stack.isEmpty()) {
                    str2 += stack.pop();
                }
                str2 += ' ';
            }

        }
        System.out.println(str2);

    }
}
