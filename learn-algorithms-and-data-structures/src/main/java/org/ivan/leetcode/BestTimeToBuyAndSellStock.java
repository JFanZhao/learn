package org.ivan.leetcode;

/**
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-17 09:25
 **/
public class BestTimeToBuyAndSellStock {
    /**
     * 动态规划
     * 状态定义：dp[i] 表示第i天股票的最低价
     * 状态定义：dp[i] = min(prices[i],dp[i-1])//当前价和前置最低价的最小值
     * 初始化：dp[0] = prices[0] 当i为0时 dp[0] = prices[i]
     * 此外还要计算最大收益max = max(max,prices[i] - dp[i] )
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        if (prices == null || prices.length == 0){
            return max;
        }

        int length = prices.length;
        int[] dp = new int[length];
        dp[0] = prices[0];
        for (int i = 1; i <length; i++) {
            dp[i] = Math.min(prices[i],dp[i-1]);
            max = Math.max(max,prices[i] - dp[i]);
        }
        return max;
    }

    /**
     * 优化空间复杂度，由于当天的价格只和前一天的有关系，所以只需要定义一个pre即可，不用数组
     * @param prices
     * @return
     */
    public int maxProfitN(int[] prices) {
        int max = 0;
        if (prices == null || prices.length == 0){
            return max;
        }
        int pre = prices[0];
        for (int i = 1; i <prices.length; i++) {
            pre = Math.min(prices[i],pre);
            max = Math.max(max,prices[i] - pre);
        }
        return max;
    }

    /**
     * 优化空间复杂度，由于当天的价格只和前一天的有关系，所以只需要定义一个pre即可，不用数组
     * @param prices
     * @return
     */
    public int maxProfitN1(int[] prices) {
        int max = 0;
        if (prices == null || prices.length == 0){
            return max;
        }
        int pre = prices[0];
        for (int i = 1; i <prices.length; i++) {
            if (prices[i] < pre){
                pre = prices[i];
            }
            if (prices[i] - pre >max){
                max = prices[i] - pre;
            }
        }
        return max;
    }


    /**
     * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润。
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices){
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(new BestTimeToBuyAndSellStock().maxProfit(new int[]{7,6,4,3,1}));
    }
}
