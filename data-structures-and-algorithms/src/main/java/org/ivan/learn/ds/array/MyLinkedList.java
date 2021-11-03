package org.ivan.learn.ds.array;

/**
 * 单向链表的基本操作
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-17 21:32
 **/
public class MyLinkedList {

    private Node head;
    private Node last;
    private int size;

    public MyLinkedList() {
        head = last = null;
        size = 0;
    }

    /**
     * 插入链表数据
     * 链表为空，直接插入
     * 插入头部：直接讲新的数据指向head，当前节点为head
     * 插入中间：将插入位置的元素指向新插入节点，新节点指向原来的next
     * 插入尾部：last指向当前节点
     * @param index 插入位置
     * @param data 插入数据
     */
    public void insert(int index, int data) throws Exception{
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        //空链表
        Node node = new Node(data);
        if (size == 0) {
            head = last = node;
        } else if (index == 0) {
            //插入头部
            node.next = head;
            head = node;
        } else if (index == size) {
            //插入尾部
            last.next = node;
            last = node;
        }else {
            //插入中间
            Node preNode = get(index - 1);
            node.next = preNode.next;
            preNode.next = node;
        }

        size++;
    }

    /**
     * 删除节点
     * @param index 删除的位置
     * @return
     */
    public Node remove(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node removedNode = null;
        if (index == 0) {
            //删除头节点
            removedNode = head;
            head = head.next;
            if (size == 1) {
                last = null;
            }
        } else if (index == (size - 1)) {
            //删除尾结点
            Node node = get(index - 1);
            removedNode = node.next ;
            node.next = null;
            last = node;
        }else {
            //删除中间节点
            Node preNde = get(index - 1);
            Node nextNode = preNde.next.next;
            removedNode = preNde.next;
            preNde.next = nextNode;
        }
        size--;
        return removedNode;
    }
    /**
     * 链表的查找
     * @param index 查找的位置
     * @return
     */
    public Node get(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void output(){
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    /**
     * 节点
     */
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
