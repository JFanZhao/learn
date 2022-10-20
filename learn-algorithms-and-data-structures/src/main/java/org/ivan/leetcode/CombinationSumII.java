package org.ivan.leetcode;

import java.util.*;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-19 21:55
 **/
public class CombinationSumII {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int length = candidates.length;
        if (length == 0) {
            return res;
        }

        Arrays.sort(candidates);
        backtrack(candidates, length, 0, target);

        return res;

    }

    private void backtrack(int[] candidates, int length, int begin, int target) {

        if (target == 0) {
            res.add(new ArrayList<>(path));
        }

        for (int i = begin; i < length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.addLast(candidates[i]);
            backtrack(candidates, length, i+1, target - candidates[i]);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        CombinationSumII solution = new CombinationSumII();
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> res = solution.combinationSum(candidates, target);
        System.out.println("输出 => " + res);

    }
}
