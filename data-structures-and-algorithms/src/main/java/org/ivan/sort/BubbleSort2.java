package org.ivan.sort;

import java.util.Arrays;

/**
 * 冒泡排序优化版本2：当已经有序了之后，不需要再继续进行比较
 * 加入标志位，判断
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 21:45
 **/
public class BubbleSort2 {
    public static void sort(int arrry[]) {
        for (int i = 0; i < arrry.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < arrry.length - i - 1; j++) {
                int tmp = 0;
                if (arrry[j] > arrry[j + 1]) {
                    //交换
                    tmp = arrry[j];
                    arrry[j] = arrry[j+1];
                    arrry[j + 1] = tmp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,8,6,3,9,2,1,7};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
