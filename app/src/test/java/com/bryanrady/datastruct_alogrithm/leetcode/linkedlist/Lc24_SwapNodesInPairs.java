package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc24_SwapNodesInPairs {

    /**
     * 链表02 - 成对交换链表节点 - 中等 - 24

     给定一个链表，为每两个相邻节点做一次交换给定一个链表，为每两个相邻节点做一次交换。
     要求不能修改节点的值，只能修改链表结构。要求O(1)的空间复杂度。

     比如：1->2->3->4，应该返回2->1->4->3。
     */

    public ListNode swapPairs(ListNode head) {
        if (head == null||head.next==null)
            return head;
        ListNode current = head;
        ListNode next = current.next;
        ListNode result = next;
        ListNode pre = null;
        while (next != null && current != null)
        {
            if (pre != null)
                pre.next = next;
            current.next = next.next;
            next.next = current;

            pre = current;
            current = current.next;
            if (current != null)
                next = current.next;
        }
        return result;
    }

    /**
     * 4ms leetcode 上的
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode  = head.next;
        head.next = swapPairs1(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }
}
