package org.ivan.leetcode;

/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-03 16:19
 **/
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        ListNode cur = head;
        while (cur != null && cur.next !=null ){
            if (cur.val == cur.next.val) {
                int x = cur.val;
                while (cur != null && cur.val == x) {
                    cur = cur.next;
                }
                pre.next = cur;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }
}
