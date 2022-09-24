package org.ivan.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-15 15:52
 **/
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                return new int[]{left, right};
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[0];
    }

    public int[] twoSum1(int[] nums, int target) {

        //key 数字，value 对应下标
        Map<Integer, Integer> numIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //找到了
            if (numIndex.containsKey(target - nums[i])) {
                return new int[]{numIndex.get(target - nums[i]),i};
            }
            numIndex.put(nums[i],i);
        }

        return new int[0];
    }

    public static void main(String[] args) {
        new TwoSum().twoSum(new int[]{3, 2, 4}, 9);
    }
}
