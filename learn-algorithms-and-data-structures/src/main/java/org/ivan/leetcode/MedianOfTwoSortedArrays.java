package org.ivan.leetcode;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/median-of-two-sorted-arrays
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-24 11:02
 **/
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
//        int leftMiddle = (m + n + 1) / 2;
//        int rightMiddle = (m + n + 2) / 2;
//        int start1 = 0, start2 = 0;
//        int left = getKth(nums1, start1, nums2, start2, leftMiddle);
//        int right = getKth(nums1, start1, nums2, start2, rightMiddle);

        int total = m + n;
        int mid = total / 2;
        //偶数
        if ((total & 1) == 0) {
            return (getKthIter(nums1, nums2, mid + 1) + getKthIter(nums1, nums2, mid)) / 2.0;
        } else {
            return getKthIter(nums1, nums2, mid+1);
        }
    }


    /**
     * 二分查找 - 迭代版本
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    private double getKthIter(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int start1 = 0, start2 = 0;
        while (true) {
            //处理边界
            if (start1 == m) {
                return nums2[start2 + k - 1];
            }
            if (start2 == n) {
                return nums1[start1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[start1], nums2[start2]);
            }

            //正常情况，更新点
            int pivot1 = Math.min(start1 + k / 2, m) - 1;
            int pivot2 = Math.min(start2 + k / 2, n) - 1;
            //判断
            if (nums1[pivot1] > nums2[pivot2]) {
                k -= pivot2 - start2 + 1;
                start2 = pivot2 + 1;
            } else {
                k -= pivot1 - start1 + 1;
                start1 = pivot1 + 1;
            }

        }
    }

    /**
     * 二分查找-递归版本
     * 中心思想，比较数组的第k/2个元素的大小，把小的那个数组的前k/2个元素剔除筛选范围
     * 此时的k应为：k-剔除的大小，循环此过程，直到k=1或者其中一个数组为空，即可找到
     * 如果k=1 则结果是两个数组的最小值
     * 如果其中一个数组为空，则结果为另外一个数组的中位数
     * <p>
     * 需要更新的值：两个的数组下次要对比的pivot，
     * 其结果应为初始的位置加上k/2-1,但是要考虑特殊情况，2/k大于此时的数组的长度，取数组的最大元素坐标
     * 更新k值
     *
     * @param nums1
     * @param start1
     * @param nums2
     * @param start2
     * @param k
     * @return
     */
    private int getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        //判断特殊情况，结束递归的情况
        //数组1 没有元素可比较了，直接取数组2的中位数
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        //数组2 没有元素可比较了，直接取数组1的中位数
        if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }
        //k为1 此时已经找到，为两个数组的较小值
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        //正常情况,更新比较的位置
        int pivot1 = start1 + Math.min(k / 2, (nums1.length - start1)) - 1;
        int pivot2 = start2 + Math.min(k / 2, (nums2.length - start2)) - 1;
        //比较，递归
        //删除较小的一边元素
        if (nums1[pivot1] > nums2[pivot2]) {
            return getKth(nums1, start1, nums2, pivot2 + 1, k - (pivot2 - start2 + 1));
        }

        return getKth(nums1, pivot1 + 1, nums2, start2, k - (pivot1 - start1 + 1));
    }

    /**
     * 找到中位数的位置，不需要真正合并数组
     *
     * @param nums1
     * @param nums2
     * @param m
     * @param n
     * @return
     */
    private double iterN(int[] nums1, int[] nums2, int m, int n) {
        //left记录上一次的结果，right记录当前结果
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= (m + n) / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((left & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }

    /**
     * 合并数组，找到中位数
     *
     * @param nums1
     * @param nums2
     * @param m
     * @param n
     * @return
     */
    private double iter(int[] nums1, int[] nums2, int m, int n) {
        //数组1 为空，返回数组2的中位数
        if (m == 0 && n > 0) {
            if ((m & 1) == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {
                return nums2[n / 2];
            }
        }

        //数组2 为空，返回数组1的中位数
        if (n == 0 && m > 0) {
            if ((m & 1) == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int len = m + n;
        int[] ordered = new int[len];

        int count = 0;
        int i = 0, j = 0;
        while (count < len) {
            if (i == m) {
                while (j != n) {
                    ordered[count++] = nums2[j++];
                }
                break;
            }

            if (j == n) {
                while (i != m) {
                    ordered[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                ordered[count++] = nums1[i++];
            } else {
                ordered[count++] = nums2[j++];
            }
        }

        if ((ordered.length & 1) == 0) {
            return (ordered[len / 2 - 1] + ordered[len / 2]) / 2.0;
        } else {
            return ordered[len / 2];
        }
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}
