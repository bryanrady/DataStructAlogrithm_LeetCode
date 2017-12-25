package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by bryanrady on 2017/12/8.
 */

public class Lc92_ReverseLinkedList {

    /**
     * 链表01 - 进阶反转链表 - 中等 - 92
     反转一个链表中从m到n的元素。
     比如：对于一个链表1->2->3->4->5->NULL，m = 2， n = 4
     则返回链表 1->4->3->2->5->NULL
     注意：可以假设1<=m<=n<=链表长度。
     */

    /**
     * @param head      3ms
     * @param m     不太看得懂啊 反转
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null || head.next==null){
            return head;
        }
        //定义一个辅助头结点（不存在的头结点）
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for(int i = 0; i < m - 1; i++){ //找到反转节点的上一个节点
            prev = prev.next;
        }
        ListNode start = prev.next;     //开始反转的位置也就是m
        ListNode then = start.next;
        for(int j = m; j < n; j++){
            start.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = start.next;
        }
        return dummy.next;
    }
}
