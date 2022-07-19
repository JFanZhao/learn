package org.ivan.sort;

import org.ivan.util.ArrayGenerator;
import org.ivan.util.SortingHelper;

/**
 * 选择排序
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−06-19 10:02
 **/
public class SelectionSort {
    private SelectionSort() {
    }

    public static <E extends Comparable<E>> void  sort(E[] arr) {

        for (int i = 0; i < arr.length; i++) {
            //最小值的索引
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            //交换位置
            SortingHelper.swap(arr, i, minIndex);
        }
    }



    public static void main(String[] args) {
        int n =10000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
        SortingHelper.sortTest("SelectionSort",arr);

        n =100000;
        Integer[] arr1 = ArrayGenerator.generateRandomArray(n,n);
        SortingHelper.sortTest("SelectionSort",arr1);

    }
}
