package org.ivan.leetcode;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-increasing-subsequence
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-11 19:33
 **/
public class LongestIncreasingSubsequence {
    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    /**
     * 动态规划+二分查找
     * 遍历每个元素，如果元素大于最大值，则直接插入
     * 否则替换当前数组中第一个比当前值大的值
     *
     * @param nums
     * @return
     */
    public int lengthOfLISBi(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int end = 0;
        int[] tail = new int[nums.length];
        tail[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //大于数组中最大值，直接插入
            if (nums[i] > tail[end]) {
                end++;
                tail[end] = nums[i];
            } else {
                //二分查找
                int left = 0;
                int right = end;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (tail[mid] < nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tail[left] = nums[i];
            }

        }
        return ++end;

    }

}
