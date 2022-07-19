package org.ivan.learn.ds.demo;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−04-19 19:25
 **/
public class QuickSort {
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        //结束递归
        if (startIndex >= endIndex) {
            return;
        }

        int pivot = partition(arr, startIndex, endIndex);
        quickSort(arr, startIndex, pivot - 1);
        quickSort(arr, pivot + 1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int mark = startIndex;
        int pivot = arr[startIndex];
        int p = 0;
        for (int i = startIndex+1; i <= endIndex; i++) {
            //如果元素比基准元素小，mark边界右移一位，交换元素
            if (arr[i] < pivot) {
                mark++;
                p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }
        //循环结束，交换mark和pivot的位置
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {4,4,6,5,3,2,8,1};
//        quickSort(arr, 0, arr.length-1);
//        System.out.println(Arrays.toString(arr));
        IntStream.range(0,3).forEach(System.out::println);
    }
}
