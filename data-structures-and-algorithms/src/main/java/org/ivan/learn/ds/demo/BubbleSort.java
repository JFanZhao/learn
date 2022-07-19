package org.ivan.learn.ds.demo;

import java.util.Arrays;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−04-19 08:38
 **/
public class BubbleSort {
    public static int[] sort(int array[]) {
        if (array == null || array.length < 2) {
            return array;
        }

        //找出无序数列的边界，每次遍历只遍历无需数列内容即可
        int lastExchangedIndex = 0;
        int sortBorder = array.length - 1;

        for (int i = 0; i < array.length - 1; i++) {
            int tmp = 0;
            //若存在交换，则数组无序，无则数组已经有序，结束循环数组有序
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    isSorted = false;
                    //交换
                    tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                    lastExchangedIndex = j;
                }
            }
            sortBorder = lastExchangedIndex;
            if (isSorted) {
                break;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
