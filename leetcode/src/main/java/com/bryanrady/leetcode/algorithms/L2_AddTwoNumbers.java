package com.bryanrady.leetcode.algorithms;

public class L2_AddTwoNumbers {

//    Example 1:
//    Input: l1 = [2,4,3], l2 = [5,6,4]
//    Output: [7,0,8]
//    Explanation: 342 + 465 = 807.
//
//    Example 2:
//    Input: l1 = [0], l2 = [0]
//    Output: [0]
//
//    Example 3:
//
//    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//    Output: [8,9,9,9,0,0,0,1]

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list = new ListNode();
        while (l1.next != null){
            l1 = l1.next;
            l1.next = null;
        }
        System.out.println(l1);
        return list;
    }

    /**
     * 单链表
     */
    static class ListNode{
        int val;
        ListNode next;

        ListNode(){}

        ListNode(int val){
            this.val = val;
        }

        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

}
