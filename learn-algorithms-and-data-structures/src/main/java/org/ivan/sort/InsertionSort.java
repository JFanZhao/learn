package org.ivan.sort;

import java.util.Arrays;
import org.ivan.util.ArrayGenerator;
import org.ivan.util.SortingHelper;

/**
 * 插入排序，往前插入到合适的位置
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−06-19 10:44
 **/
public class InsertionSort {
    private InsertionSort() {
    }

    public static <E extends Comparable> void sort(E[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                SortingHelper.swap(arr, j, j - 1);
            }
        }
    }


    /**
     * 插入排序，不需要交换位置，记录当前值，循环找到合适的位置，不合适的直接往后移位赋值即可
     *
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable> void sort2(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E t = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] =t;
        }
    }

    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for(int n: dataSize){

            System.out.println("Random Array : ");

            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("InsertionSort2", arr2);

            System.out.println();

            System.out.println("Ordered Array : ");

            arr = ArrayGenerator.generateOrderedArray(n);
            arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("InsertionSort2", arr2);

            System.out.println();
        }
    }
}
