package org.ivan.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-08 22:04
 **/
public class GenerateParentheses {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {

        if (n <= 0) {
            return res;
        }
        generateParenthesis("", n, n);

        return res;

    }

    /**
     *
     * @param s
     * @param left
     * @param right
     */
    private void generateParenthesis(String s, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left == right) {
            generateParenthesis(s+"(",left-1,right);
        } else if (left < right) {
            if (left > 0) {
                generateParenthesis(s + "(", left - 1, right);
            }
            generateParenthesis(s + ")", left, right - 1);
        }
    }
}
