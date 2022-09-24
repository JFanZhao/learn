package org.ivan.leetcode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-20 11:18
 **/
public class MergeTwoSortedLists {
    /**
     * 递归
     * 核心是比较
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }

    /**
     * 迭代
     * 创建一个新的头结点
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoListsIter(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode pre = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            }else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }

        pre.next = list1 == null ? list2 : list1;
        return dummyNode.next;
    }
}
