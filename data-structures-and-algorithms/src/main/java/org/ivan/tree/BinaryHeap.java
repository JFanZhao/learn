package org.ivan.tree;

import java.util.Arrays;

/**
 * 二叉堆，以最小堆为例
 * 三个操作：
 * 插入节点：插入在最后一个节点上，然后循环与父节点比较，如果比父节点小，则交换位置，一遍遍的上浮，直到不能上浮
 * 删除节点：删除顶点，最后一个节点临时放到顶点，和孩子节点比较，与最小的孩子节点交换，一遍遍的下沉，直到无法下沉
 * 构建：所有非叶子结点下沉的过程
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-27 22:01
 **/
public class BinaryHeap {
    /**
     * 上浮调整：插入在最后一个节点上，然后循环与父节点比较，如果比父节点小，则交换位置，一遍遍的上浮，直到不能上浮
     * @param array
     */
    public static void upAdjust(int[] array) {
        //左 2*parent +1 右 2*parent+2
        int childIndex = array.length - 1;
        int parentIndex = (childIndex -1)/2;
        //保存插入的叶子结点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉操作
     * @param array
     * @param parentIndex 要下沉的父节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        //保存父节点的值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex < length) {
            //如果有右孩子，且右孩子值小于左孩子的值，则定位到右孩子
            if (childIndex + 1 <= length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            //如果父节点小于任何一个孩子节点，则直接跳出，不需要下沉
            if (temp < array[childIndex]) {
                break;
            }
            //无须真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;

        }
        array[parentIndex] = temp;
    }

    public static void buildHeap(int[] array) {
        //从最后一个非叶子结点开始，以此下沉
        for (int i = (array.length-2)/2; i >=0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
