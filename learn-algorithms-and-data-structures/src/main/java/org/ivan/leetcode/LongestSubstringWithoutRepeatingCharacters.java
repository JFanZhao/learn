package org.ivan.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−07-14 10:54
 **/
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public int lengthOfLongestSubstring1(String s) {
        int res = 0;
        Set<Character> ols = new HashSet<>();
        int r = -1;
        int l = 0;
        while (r +1< s.length()) {
            //不包含，移动右指针
            if (!ols.contains(s.charAt(r+1))) {
                ols.add(s.charAt(r+1));
                r++;
            } else {
                //已存在 移动左指针，删除元素
                ols.remove(s.charAt(l));
                l++;
            }
            res = Math.max(res, r - l +1);
        }

        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        int res=0, r= -1;
        Set<Character> ols = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            //移动左指针的时候，删除窗口最左边的元素
            if (i != 0) {
                ols.remove(s.charAt(i-1));
            }
            //移动右指针
            while (r+1 < n && !ols.contains(s.charAt(r+1))) {
                ols.add(s.charAt(r+1));
                r++;
            }
            res = Math.max(res, r - i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring1("pwwkew"));
    }
}
