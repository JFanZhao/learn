package org.ivan.leetcode;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-04 20:50
 **/
public class MergeKSortedLists {
    /**
     * 普通循环
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

//        ListNode res = null;
//        for (int i = 0; i < lists.length; i++) {
//            mergeTwoLists(res, lists[i]);
//        }
//        return res;

        if (lists == null || lists.length < 1) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {

        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l = merge(lists, left, mid);
        ListNode r = merge(lists, mid + 1, right);
        return mergeTwoLists(l, r);
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummyNode = new ListNode(-1);
        ListNode pre = dummyNode;
        while (a != null && b != null) {
            if (a.val < b.val) {
                pre.next = a;
                a = a.next;
            } else {
                pre.next = b;
                b = b.next;
            }
            pre = pre.next;
        }
        pre.next = a == null ? b : a;
        return dummyNode.next;
    }
}
