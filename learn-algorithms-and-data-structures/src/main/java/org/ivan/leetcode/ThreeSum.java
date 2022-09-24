package org.ivan.leetcode;

import com.sun.jmx.snmp.SnmpNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-14 16:40
 **/
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        //排序
        Arrays.sort(nums);
        int i =0;
        while (i < nums.length - 2) {
            twoSum(nums,i,result);

            int tmp = nums[i];
            while (i < nums.length && tmp == nums[i]) {
                i++;
            }
        }
        return result;
    }

    private void twoSum(int[] nums, int i, List<List<Integer>> result) {
        int j = i+1;
        int k = nums.length - 1;
        int target = - nums[i];
        while (j < k) {
            //找到
            if (nums[j] + nums[k] == target) {
                //添加
                result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                int tmp = nums[j];
                int tm1 = nums[k];
                while (j < k && nums[j] == tmp) {
                    j++;
                }
                while (j < k && nums[k] == tm1) {
                    k--;
                }
            }else if(nums[j] + nums[k] < target){
                j++;
            }else {
                k--;
            }
        }
    }

    public static void main(String[] args) {
        new ThreeSum().threeSum(new int[]{-1,0,1,2,-1,-4});
    }
}
