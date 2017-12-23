package com.bryanrady.datastruct_alogrithm.leetcode.linkedlist;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc2_AddTwoNumbers {

    /**
     * 链表03 - 链表相加低阶 - 中等 - 2

     给定两个非空链表，分别代表两个非负整数。链表中的数字以逆序排列并且每个节点只包含一个个位数。
     将两个链表所代表的数字相加并且以链表的形式返回这个和。

     比如：输入2->4->3和5->6->4
     应该返回 7->0->8
     */

    /**
     * 64ms
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return null;
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode prev = null;
        int jinwei = 0;  // 进位
        while(node1 != null){
            node1.val += jinwei;
            if(node2 != null){
                node1.val = node2.val + node1.val;
                node2 = node2.next;
            }
            jinwei = node1.val / 10;
            node1.val = node1.val % 10;
            prev = node1;
            node1 = node1.next;
        }
        while(node2 != null){  // 链表1比较短的情况
            node2.val += jinwei;
            jinwei = node2.val / 10;
            node2.val = node2.val % 10;
            prev.next = node2;
            node2 = node2.next;
            prev = prev.next;
        }
        if(jinwei > 0)  // 最后还要进一位
            prev.next = new ListNode(jinwei);
        return l1;
    }

    /**
     * leetcode上的 48ms
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode list = new ListNode(0);
        ListNode head = list;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0){
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            ListNode temp = new ListNode(sum % 10);
            carry = sum/10;
            head.next = temp;
            head = temp;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return list.next;
    }
}
