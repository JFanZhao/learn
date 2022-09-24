package org.ivan.leetcode;

/**
 * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润。
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 *      总利润为 4 + 3 = 7 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-17 09:25
 **/
public class BestTimeToBuyAndSellStockII {
    /**
     * 动态规划
     * <p>状态定义：<br/>dp[i][j] j有两个值：为0时表示，在第i天，手中未持有股票的最大收益;<br/>
     *          j为1的时候表示，在第i天，手中持有股票的最大收益</p>
     * <p>状态转移 ：<br/>1. 未持有股票：dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i])<br/>
     *              取前一天的最大收益和卖出股票后（卖出所得持有股票+当天的股价）的最大收益<br/>
     *          2. 持有股票：dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i])<br/>
     *              取前一天持有股票的收益和买入当天股票后的最大收益（买入即当前未持有股票的最大收益-当天的股价）</p>
     * <p>初始值： <br/> 1. 未持有股票：dp[0][0] = 0 第一天未操作，没有任何收益<br/>
     *          2. 持有股票：dp[0][1] = -prices[0] 买入股票，第一天收益为负</p>
     * 返回值：返回dp[n-1][0] 即最大的未持有股票的收益
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n= prices.length;
        //定义状态数组
        int[][] dp = new int[prices.length][2];
        //初始值
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        //状态转移 计算
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * 动态规划 优化版，节省空间。
     * @param prices
     * @return
     */
    public int maxProfitOp(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n= prices.length;
        //定义状态数组
        //初始值
        int dp0 = 0;
        int dp1 = -prices[0];
        //状态转移 计算
        for (int i = 1; i < n; i++) {
            int ndp0 = Math.max(dp0, dp1 + prices[i]);
            int ndp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = ndp0;
            dp1 = ndp1;
        }
        return dp0;
    }

    /**
     * 贪心
     * @param prices
     * @return
     */
    public int maxProfitGreedy(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n= prices.length;
        //定义状态数组
        //初始值
        int res = 0;
        //状态转移 计算
        for (int i = 1; i < n; i++) {
            res +=Math.max(0,prices[i]-prices[i-1]);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(new BestTimeToBuyAndSellStockII().maxProfitGreedy(new int[]{7,1,5,3,6,4}));
    }
}
