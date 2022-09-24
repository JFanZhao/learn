package org.ivan.leetcode;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * leetcode 53题
 * https://leetcode.cn/problems/maximum-subarray/
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-20 10:34
 **/
public class MaximumSubarray {
    /**
     * 动态规划
     * 状态定义:dp[i] 表示第i个元素最大的连续子数组的和
     * 状态转移：当dp[i-1]小于0等位nums[i],否则为dp[i-1]+nums[i]
     * 初始值：dp[0] = nums[0]
     * 输出：dp[n]中最大的值
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //定义结果
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        //定义状态数组
        int[] dp = new int[length];
        //初始值
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            }else {
                dp[i] = dp[i - 1] + nums[i];
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 动态规划 优化空间
     * 状态定义:dp[i] 表示第i个元素最大的连续子数组的和
     * 状态转移：当dp[i-1]小于0等位nums[i],否则为dp[i-1]+nums[i]
     * 初始值：dp[0] = nums[0]
     * 输出：dp[n]中最大的值
     * @param nums
     * @return
     */
    public int maxSubArrayOp(int[] nums) {
        //定义结果
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int pre = 0;
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            res = Math.max(res, pre);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArrayOp(new int[]{-1}));
    }
}
