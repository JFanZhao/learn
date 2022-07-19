package org.ivan.learn.ds.array;

/**
 * 队列基本操作，入队，出队  循环队列
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-19 16:40
 **/
public class MyQueue {
    private int[] array;
    private int front;
    private int rear;

    public MyQueue(int capacity) {
        array = new int[capacity];
    }

    /**
     * 入队
     * @param element
     */
    public void enQueue(int element) throws Exception{
        if ((rear + 1) % array.length == front) {
            throw new Exception("队列已满！");
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
    }

    public int deQueue() throws Exception{
        if (rear == front) {
            throw new Exception("队列已空");
        }
        int deQueueElement = array[front];
        front = (front + 1) % array.length;
        return deQueueElement;
    }
}




