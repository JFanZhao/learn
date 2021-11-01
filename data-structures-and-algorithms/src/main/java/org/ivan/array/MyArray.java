package org.ivan.array;

import java.sql.Array;
import java.util.Arrays;

/**
 * 数组的基本操作
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-17 20:51
 **/
public class MyArray {
    private int[] array;
    /**
     * 当前数据大小
     */
    private int size;

    public MyArray(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    /**
     * 获取指定下标元素
     *
     * @param index 待获取的数据的下标
     * @return
     */
    public int get(int index) throws Exception {
        if (index > size) {
            throw new IndexOutOfBoundsException("超出数据实际元素范围");
        }
        return array[index];
    }

    /**
     * 数据插入，如果超过数据大小，进行扩容
     * 数据插入思路：
     *
     * @param index   插入新元素的下标
     * @param element 新插入的数据
     */
    public void insert(int index, int element) throws Exception {
        if (index > size) {
            throw new IndexOutOfBoundsException("超出数据实际元素范围");
        }
        //扩容
        if (size >= array.length) {
            resize();
        }
        //移动数据位置，插入数据;从右向左移动
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
    }

    /**
     * 扩容数据，大小是原来的一倍
     */
    public void resize() {
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * 删除数组的元素
     *
     * @param index 待删除的下标
     * @return
     */
    public int delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出数据实际元素范围");
        }
        int deleteElement = array[index];
        //删除元素,从左到右，将元素追个左移一位
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deleteElement;
    }

    public void output() {
        Arrays.stream(array).forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        MyArray myArray = new MyArray(10);
        myArray.insert(0, 9);
        myArray.insert(1, 7);
        myArray.insert(2, 8);
        myArray.insert(3, 3);
//        myArray.insert(10,3);
        myArray.delete(4);
        myArray.output();
    }
}
