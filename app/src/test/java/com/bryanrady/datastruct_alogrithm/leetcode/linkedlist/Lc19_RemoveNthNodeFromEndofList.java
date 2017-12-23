package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc19_RemoveNthNodeFromEndofList {

    /**
     *链表07 - 从链表中删除倒数第N个元素 - 中等 - 19

     【题中包含的进阶技术：双指针技术】

     给定一个链表，删除其倒数第N个元素并返回头结点。

     比如：

     给定： 1->2->3->4->5， n = 2，
     则应该返回 1->2->3->5。

     注意：
     n为有效值。

     提示：使用双指针技术来实现只用一次遍历求解。
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur.next != null && n > 0) {
            cur = cur.next;
            n--;
        }
        ListNode tmp = dummy;
        while(cur.next != null) {
            tmp = tmp.next;
            cur = cur.next;
        }
        tmp.next = tmp.next.next;
        return dummy.next;
    }
}
