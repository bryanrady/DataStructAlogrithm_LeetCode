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
        ListNode result = new ListNode(-1);
        result.next=head;
        ListNode fast=head,slow=result;
        while(fast!=null){
            if(fast.val==val){
                slow.next=fast.next;
            }else{
                slow=slow.next;
            }
            fast=fast.next;
        }
        return result.next;
    }
}
