package org.ivan.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−06-25 12:06
 **/
public class MergeSort {
    public static void mergeSort(int[] arr, int startIndex, int endIndex,int[] temp) {

        //结束递归
        if (startIndex < endIndex) {
            int mid = (startIndex + endIndex) / 2;
            mergeSort(arr, startIndex, mid,temp);
            mergeSort(arr, mid + 1, endIndex,temp);

            if (arr[mid] > arr[mid + 1]) {
                merge(arr, startIndex, endIndex, mid,temp);
            }

        }



    }

    private static void merge(int[] arr, int startIndex, int endIndex, int mid,int[] temp) {
        //临时数组，存储合并后的结果
        int p1 = startIndex;
        int p2 = mid + 1;
        int p = 0;
        //比较两个集合的元素，以此放入临时数组
        while ((p1 <= mid) && (p2 <= endIndex)) {
            if (arr[p1] <= arr[p2]) {
                temp[p++] = arr[p1++];
            } else {
                temp[p++] = arr[p2++];
            }
        }
        //结束后，如果两边还剩余元素，直接插入临时数组
        while (p1 <= mid) {
            temp[p++] = arr[p1++];
        }
        while (p2 <= endIndex) {
            temp[p++] = arr[p2++];
        }
        p =0;
        //降临时数组的数据拷贝到原数组内
        while (startIndex <= endIndex) {
            arr[startIndex++] = temp[p++];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1,temp);
        System.out.println(Arrays.toString(arr));
    }
}
