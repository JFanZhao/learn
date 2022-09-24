package org.ivan.leetcode;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-strings
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-06 10:36
 **/
public class AddStrings {
    public String addStrings(String num1, String num2) {

        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;

        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = x + y + add;
            add = sum / 10;
            res.append(sum % 10);
            i--;
            j--;
        }

        return res.reverse().toString();
    }
}
