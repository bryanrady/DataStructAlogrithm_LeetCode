package com.bryanrady.datastruct_alogrithm.leetcode.greedyalogrithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc435_Non_overlappingIntervals {

    /**
     * 贪心算法03 - 非重叠区间 - 中等 - 435

     给定一组区间，问最少删除多少个区间，可以让这些区间之间互相不重叠。

     * 给定区间的起始点永远小于终止点
     * [1,2]和[2,3]这种不叫重叠。

     如[[1,2],[2,3],[3,4],[1,3]]，算法返回1，因为要删除[1,3]这个区间。
     如[[1,2,[1,2],[1,2]]]，返回2。
     */

    public int eraseOverlapIntervals(Interval[] intervals) {
        int high = 0, index = 0, count = 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start == i2.start ? i1.end - i2.end : i1.start - i2.start;
            }
        });
        if (intervals.length < 2) {
            return 0;
        }
        high = intervals[0].end;
        index = 1;
        while (index < intervals.length) {
            Interval curr = intervals[index];
            if (curr.start < high) {
                ++count;
                high = Math.min(high, curr.end);
            } else {
                high = curr.end;
            }
            ++index;
        }
        return count;
    }

    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
}
