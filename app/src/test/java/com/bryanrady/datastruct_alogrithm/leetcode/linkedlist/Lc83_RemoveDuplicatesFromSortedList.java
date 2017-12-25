package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc83_RemoveDuplicatesFromSortedList {

    /**
     *链表06 - 从有序链表中删除重复元素 - 简单 - 83

     【题中包含的进阶技术：虚拟头结点】

     给定一个有序链表，删除其中所有重复的元素，只留下不存在重复的元素。

     比如：
     给出1->2->3->3->4->4->5，返回1->2->5，
     给定1->1->2->3->3 返回 1->2->3
     */

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null)
            return null;
        ListNode cur = head.next;
        ListNode pre = head;
        while(cur != null){
            if(cur.val == pre.val){
                if(cur.next != null){
                    cur.val = cur.next.val;
                    cur.next = cur.next.next;
                }else{
                    pre.next = null;
                    cur = null;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}
