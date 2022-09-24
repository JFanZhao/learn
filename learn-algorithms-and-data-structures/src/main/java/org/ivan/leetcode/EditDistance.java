package org.ivan.leetcode;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/edit-distance
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-24 10:12
 **/
public class EditDistance {
    /**
     * 动态规划
     * 特殊边界情况：如果其中一个数组长度为0，直接返回另外一个数组的长度
     * 初始化：任意一个数组为空，另外一个不为空，相当于纯粹的插入和删除操作，因此：
     * dp[i][0] = i;
     * dp[0][j]=j;
     * 状态转移：
     * 三种操作：①：插入 dp[i][j] = dp[i][j-1]+1;
     * ②：删除 dp[i][j] = dp[i-1][j] +1;
     * ③：修改 dp[i][j] = dp[i-1][j-1] +1;
     * dp[i][j] = min(三种操作)
     * 另外，当第i和第j个字符相等时，dp[i][j] = dp[i-1][j-1]，无任何操作
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        //其中一个数组长度为0的情况
        if (m * n == 0) {
            return m + n;
        }
        //定义状态数组
        int[][] dp = new int[m + 1][n + 1];
        //初始化
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        //状态转移方程
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        System.out.println(new EditDistance().minDistance(word1,word2));
    }
}
