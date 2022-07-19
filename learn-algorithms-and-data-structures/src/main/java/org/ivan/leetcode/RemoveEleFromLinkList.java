package org.ivan.leetcode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * https://leetcode.cn/problems/remove-linked-list-elements/
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−06-24 10:43
 **/
public class RemoveEleFromLinkList {
    /**
     * 循环删除链表中指定的元素
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        while (pre.next != null) {
            //删除
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }
        return head;
    }

    /**
     * 使用虚拟头节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1, head);

        ListNode pre = dummyHead.next;
        while (pre.next != null) {
            //删除
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            }else {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 递归删除
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {

        //递归终止条件
        if (head == null) {
            return null;
        }
        //递归
        head.next = removeElements3(head.next,val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new RemoveEleFromLinkList()).removeElements3(head, 6);
        System.out.println(res);
    }
}
