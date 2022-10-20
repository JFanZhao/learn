package org.ivan.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-20 07:27
 **/
public class Combinations {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n < k) {
            return res;
        }
        backtracking(n, k, 1);
        return res;
    }

    private void backtracking(int n, int k, int begin) {
        if (k == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }
        /**
         * 剪枝
         */
        for (int i = begin; i <= n-(k-path.size())+1; i++) {
            path.addLast(i);
            backtracking(n, k, i+1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Combinations solution = new Combinations();
        int n = 5;
        int k = 3;
        List<List<Integer>> res = solution.combine(n, k);
        System.out.println(res);

    }
}
