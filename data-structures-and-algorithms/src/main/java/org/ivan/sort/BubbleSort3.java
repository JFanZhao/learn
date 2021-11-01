package org.ivan.sort;

import java.util.Arrays;

/**
 * 冒泡排序优化版本2：对于一开始后面就是相对有序的情况，每次都要比较是很浪费的，可以加入一个最后交换的位置，在这之后的都是有序的了，不需要进行比较了
 *
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 21:45
 **/
public class BubbleSort3 {
    public static void sort(int arrry[]) {
        //记录最后一次交换的位置
        int lastExchangIndex = 0;
        //无需数列的边界，每次循环到此即可
        int orderBorder = arrry.length - 1;
        for (int i = 0; i < arrry.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < orderBorder; j++) {
                int tmp = 0;
                if (arrry[j] > arrry[j + 1]) {
                    //交换
                    tmp = arrry[j];
                    arrry[j] = arrry[j + 1];
                    arrry[j + 1] = tmp;
                    isSorted = false;
                    //有交换时，更新最后一次交换的位置
                    lastExchangIndex = j;
                }
            }
            //每次循环结束，更新边界
            orderBorder = lastExchangIndex;
            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}