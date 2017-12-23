package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc143_ReorderList {

    /**
     *链表09 - 重排链表 - 难 - 143

     【题中包含的进阶技术：双指针技术】

     给定一个链表L: L0→L1→…→Ln-1→Ln,将其重排序为 L0→Ln→L1→Ln-1→L2→Ln-2→…

     注意：
     原地排序，并且请思考如何只用一次遍历求解。

     比如：
     1->2->3->4，返回1->4->2->3。
     */

    public void reorderList(ListNode head) {
        //Tricky part is hard to trace tail and then tail.prev
        //solution: reverse the back half so we can iterater from tail -> tail.next

        //find mid
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        //reverse the part after mid
        ListNode cur = slow.next;
        while(cur != null && cur.next != null){
            ListNode tempN = cur.next;
            cur.next = tempN.next;
            tempN.next = slow.next;
            slow.next = tempN;
        }

        //relink
        ListNode iter1 = head;       //head
        ListNode iter2 = slow.next;  //head of reversed part
        slow.next = null;            //break 2 lists to avoid cycle
        while(iter1 != null && iter2 != null){
            ListNode next1 = iter1.next;
            ListNode next2 = iter2.next;
            iter1.next = iter2;
            iter2.next = next1;
            iter1 = next1;
            iter2 = next2;
        }

    }
}
