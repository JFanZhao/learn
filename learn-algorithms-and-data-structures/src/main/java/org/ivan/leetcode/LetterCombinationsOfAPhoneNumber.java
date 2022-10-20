package org.ivan.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−10-12 15:57
 **/
public class LetterCombinationsOfAPhoneNumber {
    Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.equals("")) {
            return res;
        }
        backtrack(digits, 0, new StringBuffer());
        return res;
    }

    private void backtrack(String digits, int index, StringBuffer combinations) {
        if (index == digits.length()) {
            res.add(combinations.toString());
        }else {
            char digit = digits.charAt(index);
            String ch = phoneMap.get(digit);
            for (int i = 0; i < ch.length(); i++) {
                combinations.append(ch.charAt(i));
                backtrack(digits,index+1,combinations);
                combinations.deleteCharAt(index);
            }
        }

    }

    public static void main(String[] args) {
        List<String> strings = new LetterCombinationsOfAPhoneNumber().letterCombinations("");
        System.out.println(strings);
    }
}
