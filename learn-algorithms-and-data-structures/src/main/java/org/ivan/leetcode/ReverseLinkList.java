package org.ivan.leetcode;

/**
 * 反转链表
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−06-25 11:09
 **/
public class ReverseLinkList {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 递归实现反转
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rev = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return rev;
    }
}
