package org.ivan.sort;

import java.util.Arrays;
import org.ivan.util.SortingHelper;

/**
 * 快排
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−06-28 10:56
 **/
public class QuickSort {
    private QuickSort() {
    }

    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int p = partition(arr, startIndex, endIndex);
            sort(arr, startIndex, p - 1);
            sort(arr,p+1,endIndex);
        }
    }

    private int partition(int[] arr, int startIndex, int endIndex) {
        int p = arr[startIndex];
        int mark = startIndex;
        int t = 0;
        for (int i = startIndex+1; i <= endIndex; i++) {
            if (arr[i] < p) {
                mark++;
                t = arr[mark];
                arr[mark] = arr[i];
                arr[i] =t;
            }
        }
        arr[startIndex] = arr[mark];
        arr[mark] = p;
        return mark;

    }

    public static void main(String[] args) {
        int[] arr = new int[] {4,4,6,5,3,2,8,1};
        new QuickSort().sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
