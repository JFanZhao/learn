package org.ivan.leetcode;

/**
 * k个一组链表反转
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
 * 、 *
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-14 16:02
 **/
public class ReverseNodesInnKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        //虚拟头结点
        ListNode dummyHead = new ListNode(0, head);
        //一个指向前驱，一个指向待反转的最后一个节点
        ListNode pre = dummyHead;
        ListNode end = dummyHead;
        //循环到第k个节点
        while (end.next != null) {
            for (int i = 0; i < k && end!=null; i++) {
                end = end.next;
            }
            //不够k个节点
            if (end == null) {
                break;
            }
            //记住反转前的头节点
            ListNode start = pre.next;
            //记住反转后的尾结点
            ListNode next = end.next;
            end.next = null;
            //连接到反转后的头结点
            pre.next = reverseNode(start);
            //连接尾结点
            start.next = next;
            //重置节点
            pre = start;
            end = pre;

        }


        return dummyHead.next;

    }

    private ListNode reverseNode(ListNode head) {
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

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5});
        ListNode listNode = new ReverseNodesInnKGroup().reverseKGroup(head, 2);
        System.out.println(listNode);
    }
}
