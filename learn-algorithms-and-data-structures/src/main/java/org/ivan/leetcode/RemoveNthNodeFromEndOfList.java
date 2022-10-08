package org.ivan.leetcode;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-24 17:22
 **/
public class RemoveNthNodeFromEndOfList {
    /**
     * 双指针，一个指针先移动k步，第二个指针开始移动，移动的尾部的时候，第二个指针刚好在倒数n的位置，删除即可
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode first = head;
        ListNode second = dummyNode;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return dummyNode.next;
    }
}
