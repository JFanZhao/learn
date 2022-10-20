package org.ivan.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-20 07:00
 **/
public class RestoreIpAddresse {
    List<String> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>();

    /**
     * 切割 回溯
     * 判断IP段是否有效：如果IP大于1 则不能以0开头，位于1-255之间
     * 剩余的IP长度也要满足 length -i > 3*residue
     * residue 是剩余段数，从4开始
     * 找到的条件
     * 数组长度和开始长度相等，切residue为0
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        if (length < 4 || length > 12) {
            return res;
        }

        backtracking(s, length, 0, 4);

        return res;

    }

    /**
     * @param s       字符串
     * @param length  字符串长度
     * @param begin   从第几个字符开始搜索，0-length-1
     * @param residue 剩余段数
     */
    private void backtracking(String s, int length, int begin, int residue) {

        //找到一组，数组长度和开始长度相等，切residue为0
        if (begin == length) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }

        //开始搜索回溯
        for (int i = begin; i < length; i++) {
            if (begin >= length) {
                break;
            }
            //剩余的字符无法满足，剪枝
            if (residue * 3 < length - i) {
                continue;
            }

            //判断IP是否合法
            if (judgeIpSegement(s, begin, i)) {
                //找到一个合法的IP段
                path.add(s.substring(begin, i + 1));
                //递归
                backtracking(s, length, i + 1, residue - 1);
                //回溯
                path.removeLast();
            }
        }

    }

    /**
     * 判断IP是否合法
     * ip段长度大于1，则不能以0开头
     * 0-255之间
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private boolean judgeIpSegement(String s, int left, int right) {
        if (right - left > 0 && s.charAt(left) == '0') {
            return false;
        }
        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;
    }

    public static void main(String[] args) {
        List<String> strings = new RestoreIpAddresse().restoreIpAddresses("101023");
        System.out.println(strings);
    }
}
