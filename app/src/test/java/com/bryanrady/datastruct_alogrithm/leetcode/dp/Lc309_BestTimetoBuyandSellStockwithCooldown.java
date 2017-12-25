package com.bryanrady.datastruct_alogrithm.leetcode.dp;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc309_BestTimetoBuyandSellStockwithCooldown {

    /**
     * 动态规划09 - 股票交易 - 中等 - 309

     给定一个数组，表示一支股票在每一天的价格。设计一个交易算法，在这些天进行自动交易，
     要求：每天只能进行一次操作；卖完股票后，必须卖出才能再次买入；每次卖出股票后，
     下一天是不能买入的。问如何交易才能使利益最大化？

     如prices = [1,2,3,0,2]，最佳交易方式为：[buy, sell, cooldown, buy, sell],利润为3。
     */

    public int maxProfit(int[] prices) {
        int sell = 0;
        int prev_sell = 0;
        int buy = Integer.MIN_VALUE;
        int prev_buy = 0;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }
}
