package org.ivan.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−07-19 11:09
 **/
public class QuickSortNew {
    private static Random random = new Random();

    public static int topK(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (true) {
            int partition = partition(arr, left, right);
            if (partition == k-1) {
                return arr[partition];
            } else if (partition < k - 1) {
                left = partition + 1;
            } else {
                right = partition - 1;
            }
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition2(arr, left, right);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int index = left + random.nextInt(right - left + 1);
        swap(arr, left, index);
        int p = left;
        int v = arr[left];
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] > v) {
                p++;
                swap(arr, p, i);
            }
        }

        swap(arr, p, left);
        return p;
    }

    private static int partition2(int[] arr, int left, int right) {
        int index = left + random.nextInt(right - left + 1);
        swap(arr, left, index);
        int i = left + 1;
        int j = right;
        int pivot = arr[left];

        while (true) {
            while (i <= j && arr[i] < pivot) {
                i++;
            }
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, j, left);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(topK(arr, 3));
        System.out.println(Arrays.toString(arr));
    }
}
