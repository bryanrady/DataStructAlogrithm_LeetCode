package com.bryanrady.datastruct_alogrithm.leetcode.graph;

/**
 * Created by wqb on 2017/12/23.
 */

public class Lc279_PerfectSquares {

    /**
     * 图论01（最短路径，队列） - 完全平方树 - 中等 - 279

     （还记得吗，我们在动态规划中遇到过这个题。这也说明，一个问题的解法可以是多种多样的）

     给定一个正整数n，寻找最少的完全平方数（1,4,9,16...），使它们的和为n。

     比如给出n = 12，返回3，因为12 = 4 + 4 + 4；给出n = 13，返回2，因为13 = 4 + 9。

     提示： 贪心法能解决这个问题吗？
     */

    public int numSquares(int n) {
        // Based on Lagrange's Four Square theorem,
        // there are only 4 possible results: 1, 2, 3, 4.
        if (isSquare(n)) {
            return 1;
        }

        // Check whether 2 is the result.
        int sqrt = (int)Math.sqrt(n);
        for(int i = 1; i <= sqrt; i++)
        {
            if (isSquare(n - i * i))
            {
                return 2;
            }
        }

        while ((n & 3) == 0) {
            n >>= 2;
        }
        if ((n & 7) == 7) {
            return 4;
        }

        return 3;
    }

    private boolean isSquare(int num) {
        int square = (int)Math.sqrt(num);
        return num == square * square;
    }
}
