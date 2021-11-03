package org.ivan.learn.ds.sort;

import java.util.Arrays;

/**
 * 鸡尾酒排序：鸡尾酒排序和冒泡思想一致，适合大部分有序的场景，思想是从最后一个开始，先从右到左，再从左到右开始
 * 减少排序的回合数
 * 代码里稍微多了些
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 21:59
 **/
public class CockTailSort {
    public static void sort(int array[])
    {
        int tmp  = 0;
        for(int i=0; i<array.length/2; i++)
        {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换
            for(int j=i; j<array.length-i-1; j++)
            {
                if(array[j] > array[j+1])
                {
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }

            //偶数轮之前，重新标记为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for(int j=array.length-i-1; j>i; j--)
            {
                if(array[j] < array[j-1])
                {
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{2,3,4,5,6,7,8,1};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
