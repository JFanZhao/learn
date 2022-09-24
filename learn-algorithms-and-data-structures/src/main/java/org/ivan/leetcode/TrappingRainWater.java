package org.ivan.leetcode;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/trapping-rain-water
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-13 21:55
 **/
public class TrappingRainWater {
    /**
     * 动态规划
     * 数组i最多能接到的雨水，等于左边和右边最大值的最小值和height[i]的差值
     * 设置两个状态，分别用来求左边的第i个的最大值和右边第i的最大值
     * 循环数组，遍历用左边和右边最大值的较小值和当前值求差记得当值位置呢个接的雨水的最大值，求和即可
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        //求左边的最大值
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        //求右边的最大值
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        //求最大值
        int res = 0;

        for (int i = 0; i < n; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return res;

    }

    /**
     * 双指针
     * @param height
     * @return
     */
    public int trapDouble(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int leftMax = 0,rightMax = 0;
        int res =0;
        int left =0;
        int right = n-1;

        while (left < right) {

            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                res += leftMax - height[left];
                left++;
            }else {
                res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println((new TrappingRainWater().trapDouble(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})));
    }
}
