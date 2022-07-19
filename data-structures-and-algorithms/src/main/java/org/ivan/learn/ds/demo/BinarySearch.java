package org.ivan.learn.ds.demo;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−04-26 07:39
 **/
public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int start =0;
        int end = arr.length - 1;
        int mid;
        while (start <= end) {
            mid = start + ((end - start) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int arr[] = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(binarySearch(arr,174));
    }
}
