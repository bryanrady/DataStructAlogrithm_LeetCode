package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc61_RotateList {

    /**
     *链表08 - 旋转链表 - 中等 - 61

     【题中包含的进阶技术：双指针技术】

     给定一个链表，让链表向右旋转k位，其中k为非负数。

     比如: 1->2->3->4->5->NULL，k = 2
     返回: 4->5->1->2->3->NULL。
     */

    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode fast=dummy,slow=dummy;

        int i;
        for (i=0;fast.next!=null;i++)//Get the total length
            fast=fast.next;

        for (int j=i-k%i;j>0;j--) //Get the i-n%i th node
            slow=slow.next;

        fast.next=dummy.next; //Do the rotation
        dummy.next=slow.next;
        slow.next=null;

        return dummy.next;
    }
}
