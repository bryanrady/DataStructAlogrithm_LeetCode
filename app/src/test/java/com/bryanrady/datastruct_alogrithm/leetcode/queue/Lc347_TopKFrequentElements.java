package com.bryanrady.datastruct_alogrithm.leetcode.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc347_TopKFrequentElements {

    /**
     * 优先队列01 - 出现频率第k的元素 - 中等 - 347

     给定一个非空整形数组，返回出现频率第k的元素。

     比如：
     给定[1,1,1,2,2,3]，k=2，返回[1,2]。

     注意：
     * 你可以假设k为有效的值，1 <= k <= 独一元素数量
     * 算法时间复杂度必须至少为O(nlogn)。

     提示：这里提供三个思路：

     1. 扫描一遍统计频率；排序找到前k个出现频率最高的元素 O(nlogn)；
     2. 维护优先队列，O(nlogk)
     3. 维护优先队列，时间复杂度为(Onlog(n-k))
     */

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        List<Integer> bucket[] = new List[nums.length+1];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int n: nums){
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }
        int range[] = new int[max-min+1]; //Frequency
        for(int num: nums){
            range[num-min]++;
        }
        for(int i=0; i<range.length; i++){
            if(bucket[range[i]]==null)
                bucket[range[i]] = new ArrayList<>();
            bucket[range[i]].add(i+min);
        }
        for(int i=bucket.length-1; i>=0; i--){
            if(result.size()==k)
                break;
            if(bucket[i] != null)
                result.addAll(bucket[i]);
        }
        return result;
    }

}
