package com.bryanrady.datastruct_alogrithm.leetcode.queue;


/**
 * Created by wqb on 2017/12/23.
 */

public class Lc23_MergekSortedLists {

    /**
     * 优先队列02（链表） - 合并k个有序链表 - 困难 - 23

     合并k个有序的链表并返回合成的有序列表。

     提示：当k为2时，其实就是经典的归并排序中的归并过程。当这个问题解决后，
     我们就自然可以设计出k分归并排序算法了。
     */

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        int n = 2;;
        while (n < lists.length * 2) {
            for (int i = 0; i < lists.length; i += n) {
                if (i + n / 2 < lists.length) {
                    lists[i] = mergeLists(lists[i], lists[i + n / 2]);
                }
            };
            n = n * 2;
        }

        return lists[0];
    }



    public static ListNode mergeLists(ListNode r1, ListNode r2) {
        if (r1 == null) {
            return r2;
        }
        if (r2 == null) {
            return r1;
        }

        ListNode phead = null;
        if (r1.val < r2.val) {
            phead = r1;
            phead.next = mergeLists(r1.next, r2);
        } else {
            phead = r2;
            phead.next = mergeLists(r1, r2.next);
        }
        return phead;
    }
}
