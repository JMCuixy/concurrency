package org.data.structure.str;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/8 18:00
 * @Description: 假设要从主串 s = "goodgoogle" 中找到 t = "google" 子串。
 */
public class Test1 {

    public static void main(String[] args) {
        s1();
        s2();
    }

    private static void s2() {
        String s = "goodgoogle";
        String t = "google";
        int isfind = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(j)) {
                if (j == t.length() - 1) {
                    isfind = 1;
                }
                j++;
                continue;
            }
            j = 0;
        }
        System.out.println(isfind);
    }

    public static void s1() {
        String s = "goodgoogle";
        String t = "google";
        int isfind = 0;
        for (int i = 0; i < s.length() - t.length() + 1; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                int jc = 0;
                for (int j = 0; j < t.length(); j++) {
                    if (s.charAt(i + j) != t.charAt(j)) {
                        break;
                    }
                    jc = j;
                }
                if (jc == t.length() - 1) {
                    isfind = 1;
                }
            }
        }
        System.out.println(isfind);
    }

}
