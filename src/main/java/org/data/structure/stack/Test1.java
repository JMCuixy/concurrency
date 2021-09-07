package org.data.structure.stack;

import java.util.Stack;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/7 13:36
 * @Description: 例 1，给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：左括号必须与相同类型的右括号匹配，左括号必须以正确的顺序匹配。例如，{ [ ( ) ( ) ] } 是合法的，而 { ( [ ) ] } 是非法的。
 */
public class Test1 {

    public static void main(String[] args) {
        String s = "{[()()]}";
        System.out.println(isLegal(s));
    }

    private static boolean isLeft(char c) {
        if (c == '{' || c == '(' || c == '[') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isPair(char p, char curr) {
        if ((p == '{' && curr == '}') || (p == '[' && curr == ']') || (p == '(' && curr == ')')) {
            return true;
        } else {
            return false;
        }
    }

    private static String isLegal(String s) {
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (isLeft(curr)) {
                stack.push(curr);
            } else {
                if (stack.empty()) {
                    return "非法";
                }
                char p = (char) stack.pop();
                if (!isPair(p, curr)) {
                    return "非法";
                }
            }
        }

        if (stack.empty()) {
            return "合法";
        } else {
            return "非法";
        }
    }

}
