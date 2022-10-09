package org.ivan.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sliding-window-maximum
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-09 07:17
 **/
public class SlidingWindowMaximum {
    /**
     * 双端队列，存储下标，保持升序
     * 窗口内时返回最大值，判断队首元素是否在窗口内，不在则移除
     * i-k为窗口边界
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null || nums.length<2){return nums;}
        Deque<Integer> queue = new LinkedList<>();
        int length = nums.length;
        int[] res = new int[length - k + 1];
        for (int i = 0; i <length; i++) {
            //保持队列中的数组的元素从队尾到队首是递增得，以此弹出队尾不满足条件的数据
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            //添加当前元素到队尾
            queue.offerLast(i);
            //队首元素不在窗口范围内
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            //满足窗口的条件，添加元素
            if (i + 1 >= k) {
                res[i - k + 1] = nums[queue.peek()];
            }

        }
        return res;

    }
}
