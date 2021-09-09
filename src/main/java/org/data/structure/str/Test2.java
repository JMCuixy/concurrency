package org.data.structure.str;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/9/9 13:43
 * @Description: >
 * 假设有且仅有 1 个最大公共子串。比如，输入 a = "13452439"， b = "123456"。由于字符串 "345" 同时在 a 和 b 中出现，
 * 且是同时出现在 a 和 b 中的最长子串。因此输出 "345"。
 */
public class Test2 {


    /**
     * 首先，你需要对于字符串 a 和 b 找到第一个共同出现的字符，这跟前面讲到的匹配算法在主串中查找第一个模式串字符一样。
     * <p>
     * 然后，一旦找到了第一个匹配的字符之后，就可以同时在 a 和 b 中继续匹配它后续的字符是否相等。这样 a 和 b 中每个互相匹配的字串
     * 都会被访问一遍。全局还要维护一个最长子串及其长度的变量，就可以完成了。
     * <p>
     * 从代码结构来看，第一步需要两层的循环去查找共同出现的字符，这就是 O(nm)。一旦找到了共同出现的字符之后，还需要再继续查找共同出
     * 现的字符串，这也就是又嵌套了一层循环。可见最终的时间复杂度是 O(nmm)，即 O(nm²)。
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "123456";
        String b = "13452439";
        String maxSubStr = "";
        int maxLen = 0;

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    for (int m = i, n = j; m < a.length() && n < b.length(); m++, n++) {
                        if (a.charAt(m) != b.charAt(n)) {
                            break;
                        }
                        if (maxLen < m - i + 1) {
                            maxLen = m - i + 1;
                            maxSubStr = a.substring(i, m + 1);
                        }
                    }
                }
            }
        }
        System.out.println(maxSubStr);
    }
}
