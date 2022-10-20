package org.ivan.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 全排列2
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-19 21:40
 **/
public class PermutationsII {

    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    /**
     * 需要去重
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }

        int length = nums.length;
        boolean[] used = new boolean[length];
        backtrack(nums, length, 0, used);

        return res;

    }

    private void backtrack(int[] nums, int length, int depth, boolean[] used) {
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            backtrack(nums, length, depth + 1, used);
            used[i] = false;
            path.removeLast();
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        PermutationsII solution = new PermutationsII();
        List<List<Integer>> lists = solution.permuteUnique(nums);
        System.out.println(lists);
    }

}
