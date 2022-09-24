package org.ivan.leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−08-21 16:25
 **/
public class LongestPalindromicSubstring {

    /**
     * 中心扩散法
     *
     * @param s
     * @return
     */
    public String longestPalindromeExpandCenter(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(s, i, i);
            int len2 = expandCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end+1);
    }

    private int expandCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


    /**
     * 动态规划
     * 如果一个字符串是回文串，去掉开头和结尾一定还是回文串
     * 状态定义：dp[i][j] 从i...j子串是不是回文串
     * 状态转移：如果char[i]和char[j]不相等则 dp[i][j]肯定不是回文串。
     *          否则，如果去掉头尾 字符剩余即 j -1 - (i+1) +1 < 2 即j-i<3则肯定是回文串，则dp[i][j]=dp[i+1][j-1]
     * 初始化：只有一个字符时，肯定是，也就是表格的对角线上必定是true即dp[i][i] = true;
     * 输出：记录开始和最大长度，输出子串
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s){
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        if (n < 2) {
            return s;
        }
        //定义状态数组
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;
        //初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        //开始递推，填表。按照列，从左下角开始
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                //i和j不相同，不是回文串
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindromeDP("cbbd"));
    }
}
