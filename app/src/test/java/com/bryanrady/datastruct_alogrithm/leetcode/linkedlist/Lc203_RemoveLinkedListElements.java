package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc203_RemoveLinkedListElements {

    /**
     *
     链表05 - 删除链表元素 - 简单 - 203

     【题中包含的进阶技术：虚拟头结点】

     删除链表中值为val的元素。

     比如：
     给出: 1->2->6->3->4->5->6，val = 6
     返回: 1->2->3->4->5
     */

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = head;
        ListNode prev = dummy;
        while(temp!=null){
            if(temp.val == val){
                prev.next = temp.next;
            }else{
                prev = prev.next;
            }
            temp = temp.next;
        }
        return dummy.next;
    }
}
