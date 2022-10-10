package org.ivan.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/coin-change
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-09 14:47
 **/
public class CoinChange {
    /**
     * 动态规划
     * dp[i]为每个金额i需要的最小的硬币的组合数
     * 初始为最大值，dp[0]=0
     * 状态转移：遍历硬币数组，如果i小于当前coin，装不下，大于时可转移。
     * dp[i]等于dp[i]和j-coin得值的+1得较小值
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0]=0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != max) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == max ? -1 : dp[amount];

    }

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0]=0;
        for (int i = 1; i <=amount ; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] == max ? -1 : dp[amount];

    }
}
