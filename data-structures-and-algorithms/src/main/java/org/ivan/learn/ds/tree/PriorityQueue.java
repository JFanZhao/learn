package org.ivan.learn.ds.tree;

import java.util.Arrays;

/**
 * 优先队列：利用二叉堆的特性，每次出对都拿出堆顶也就是最大值，然后调整堆
 * 入队时，重新调整
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-28 21:15
 **/
public class PriorityQueue {
    private int[] array;
    private int size;

    public PriorityQueue() {
        this.array = new int[32];
    }

    /**
     * 入队
     * @param key  入队元素
     */
    public void enQueue(int key) {
        //队列长度超出范围，扩容
        if(size >= array.length){
            resize();
        }
        array[size++] = key;
        upAdjust();
    }

    /**
     * 出队
     */
    public int deQueue() throws Exception {
        if(size <= 0){
            throw new Exception("the queue is empty !");
        }
        //获取堆顶元素
        int head = array[0];
        //最后一个元素移动到堆顶
        array[0] = array[--size];
        downAdjust();
        return head;
    }
    /**
     * 上浮
     */
    private void upAdjust() {
        int childIndex = size -1 ;
        int parentIndex = (childIndex - 1) / 2;
        //temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            //无需真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1) / 2;
        }
        array[childIndex] = temp;
    }
    /**
     * 下沉调整
     */
    private void downAdjust() {
        // temp保存父节点值，用于最后的赋值
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;
        while (childIndex < size) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，直接跳出
            if (temp >= array[childIndex])
                break;
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    private void resize() {
        int newSize = size * 2;
        this.array = Arrays.copyOf(array, newSize);
    }
    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素：" + priorityQueue.deQueue());
        System.out.println("出队元素：" + priorityQueue.deQueue());
    }
}
