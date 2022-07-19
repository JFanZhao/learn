package org.ivan.learn.ds.demo;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import java.util.Arrays;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−04-26 07:17
 **/
public class MergeSort {
    public static void mergeSort(int[] arr, int startIndex, int endIndex) {
        //结束递归
        if (startIndex < endIndex) {
            int mid = (startIndex + endIndex) / 2;
            mergeSort(arr, startIndex, mid);
            mergeSort(arr, mid + 1, endIndex);

            merge(arr, startIndex, endIndex, mid);
        }



    }

    private static void merge(int[] arr, int startIndex, int endIndex, int mid) {
        //临时数组，存储合并后的结果
        int[] tempArray = new int[endIndex - startIndex + 1];
        int p1 = startIndex;
        int p2 = mid + 1;
        int p = 0;
        //比较两个集合的元素，以此放入临时数组
        while ((p1 <= mid) && (p2 <= endIndex)) {
            if (arr[p1] <= arr[p2]) {
                tempArray[p++] = arr[p1++];
            } else {
                tempArray[p++] = arr[p2++];
            }
        }
        //结束后，如果两边还剩余元素，直接插入临时数组
        while (p1 <= mid) {
            tempArray[p++] = arr[p1++];
        }
        while (p2 <= endIndex) {
            tempArray[p++] = arr[p2++];
        }
        //降临时数组的数据拷贝到原数组内
        for (int i = 0; i < tempArray.length; i++) {
            arr[i + startIndex] = tempArray[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
