package org.ivan.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Lru缓存
 * 三个方法：初始化，put，get
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−07-19 15:25
 **/
public class LRUCacheAgain {

    private Map<Integer, Node> map ;
    private DoubleList cache;
    private int cap;

    public LRUCacheAgain(int cap) {
        this.cap = cap;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * 获取缓存值</b>
     * 如果不存在，返回-1</p>
     * 存在，获取到node，并将该node设置成最新访问，返回值
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }else {
            Node node = map.get(key);
            makeRecently(node);
            return node.value;
        }
    }

    /**
     * 添加缓存</p>
     * 判断key是否存在</br>
     * 已存在：更新key的值，并将node设置成最新访问</br>
     * 不存在：判断是否已满，已满则移除最久未使用（即头部元素），未满则创建新节点，添加为最新使用
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
        //满了，删除最久未使用
        if (cache.getSize() == cap) {
            removeLastRecently();
        }

        Node node = new Node(key, value);
        addRecentLy(node);

    }

    /**
     * 将节点添加为最新使用
     * @param node
     */
    private void addRecentLy(Node node) {
        cache.addLast(node);
        map.put(node.key, node);
    }

    /**
     * 删除最久未使用
     * 1. 删除第一个节点
     * 2. map中移除节点
     */
    private void removeLastRecently() {
        Node node = cache.removeFirst();
        map.remove(node.key);
    }

    /**
     * 将节点设置成最近使用
     * 1. 删除原节点
     * 2. 将节点插入到链表尾部
     * @param node
     */
    private void makeRecently(Node node) {
        cache.remove(node);
        cache.addLast(node);
    }

    /**
     * 双向链表
     */
    class DoubleList{
        private int size;
        public Node head ,tail;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
            size =0;
        }

        public int getSize() {
            return size;
        }

        /**
         * 删除一个存在的节点
         * @param node
         */
        public void remove(Node node) {
            node.next.pre  = node.pre;
            node.pre.next = node.next;
            size--;
        }

        /**
         * 将节点放到链表尾部
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
                return  null;
            }
            Node node = head.next;
            remove(node);
            return node;
        }
    }

    /**
     * 节点
     */
    class Node{
        public Node pre,next;
        public int key,value;

        public Node(int key,int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCacheAgain lRUCache = new LRUCacheAgain(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));   // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));   // 返回 4

    }
}


