package org.ivan.leetcode;

import java.util.*;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-19 21:16
 **/
public class Permutations {
    /**
     *     存储结果
     */
    List<List<Integer>> res = new ArrayList<>();
    /**
     *     存储路径
     */
    Deque<Integer> path = new ArrayDeque<>();

    /**
     * 回溯
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return res;
        }
        int length = nums.length;
        boolean[] used = new boolean[length];
        backtrack(nums,0, length,used);
        return res;
    }

    private void backtrack(int[] nums,int depth, int length, boolean[] used) {
        //找到一个排列
        if (depth == length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < length; i++) {
            if (!used[i]){
                used[i] = true;
                path.addLast(nums[i]);
                backtrack(nums, depth + 1, length, used);
                used[i] = false;
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations solution = new Permutations();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }

}
