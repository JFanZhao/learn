package org.ivan.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU 缓存机制
 * 使用map和双向链表结构
 * 两个操作
 * get 如果key不存在 返回-1 存在则先在map中找到，返回value，再建该node，移动到链表尾部
 * <p>
 * put 判断是否存在 如果不存在，直接构建一个链表，插入到尾部，判断是否越界，如果是，删除最近未使用的；如果存在，删除旧的，
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−07-18 22:18
 **/
public class LRUCache {
    private int capcity;
    private Map<Integer, Node> map;
    DoubleList cache;

    public LRUCache(int capcity) {
        this.capcity = capcity;
        map = new HashMap<>();
        cache = new DoubleList();
    }


    /**
     * 获取缓存值，如果不存在，返回-1
     * 存在则获取node，将node放置到最新访问位置（尾部），并返回node的val
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            Node node = map.get(key);
            makeRecently(node);
            return node.value;
        }
    }

    /**
     * 如果key存在，更新值，变成最近使用
     * 不存在，新建节点，添加到最近使用，判断是否超出范围，是删除最近未使用的节点
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            makeRecently(node);
            return;
        }

        if (cache.getSize() == capcity) {
            removeLeastRecently();
        }
        Node node = new Node(key, value);
        addRecently(node);


    }


    private void removeLeastRecently() {
        Node node = cache.removeFirst();
        map.remove(node.key);
    }

    /**
     * 插入到最近使用
     *
     * @param node
     */
    private void addRecently(Node node) {
        cache.addLast(node);
        map.put(node.key, node);
    }

    /**
     * 最近使用
     * 先删除，再插入到尾部
     *
     * @param node
     */
    private void makeRecently(Node node) {
        //先删除，再放到cache的尾部
        cache.remove(node);
        cache.addLast(node);
    }


}

class DoubleList {
    private Node head, tail;
    private int size = 0;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 删除已存在的node
     *
     * @param node
     */
    public void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        size--;

    }

    /**
     * 将节点添加到尾部
     *
     * @param node
     */
    public void addLast(Node node) {

        node.pre = tail.pre;
        node.next = tail;
        tail.pre.next = node;
        tail.pre = node;

        size++;
    }

    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node node = head.next;
        remove(node);
        return node;
    }
}

class Node {
    public Node next, pre;
    public int key, value;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
