package org.ivan.learn.ds.sort;

import java.util.Arrays;

/**
 * 冒泡排序：基本思想：从第一个元素开始，与右边的元素比较，如果大于则交换，否则不交换，直到与最后一个元素比较完，开始第二轮比较
 * 以此类推，直到有序
 * 稳定排序
 * 时间复杂度O(n2)
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 21:38
 **/
public class BubbleSort {
    public static void sort(int arrry[]) {
        for (int i = 0; i < arrry.length - 1; i++) {
            for (int j = 0; j < arrry.length - i - 1; j++) {
                int tmp = 0;
                if (arrry[j] > arrry[j + 1]) {
                    //交换
                    tmp = arrry[j];
                    arrry[j] = arrry[j+1];
                    arrry[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
