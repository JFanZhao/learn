package com.ivan.learn.letcode;

/**
 * 力扣8  字符串转整数
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 *
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 *
 * 示例 1：
 *
 * 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："42"（读入 "42"）
 *            ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 * 示例 2：
 *
 * 输入：s = "   -42"
 * 输出：-42
 * 解释：
 * 第 1 步："   -42"（读入前导空格，但忽视掉）
 *             ^
 * 第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 *              ^
 * 第 3 步："   -42"（读入 "42"）
 *                ^
 * 解析得到整数 -42 。
 * 由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
 * 示例 3：
 *
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 *              ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
 *
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−05-12 15:02
 **/
public class StringToDigit {
    /**
     * 思路：从第一个不是空格的位置开始读
     * 读取 第一个是否是正负号正负号，是改变正负号的值；是不是数字，不是直接返回rev
     * 判断是否溢出，大于int最大值/10 或者等于最大值，且余数大于最大值的余数
     *             小于最小值/10 ...
     *
     * @param s
     * @return
     */
    public static int myAtoi(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int res = 0;
        //1. 取出空格
        int index = 0;
        while (index < len && chars[index] ==' ') {
            index++;
        }

        //2. 如果 字符是 '    -'直接返回
        if (len == index) {
            return res;
        }

        int sign = 1;
        //3. 判断正负号
        if (chars[index] == '-'){
            sign = -1;
            index++;
        } else if (chars[index] == '+') {
            index++;
        }

        //开始遍历
        while (index < len) {
            char c = chars[index];
            //非数字
            if (c < '0' || c>'9'){
                break;
            }
            int tmp = c-'0';
            //判断是否溢出
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && (tmp > Integer.MAX_VALUE % 10))) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && tmp > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            //构造数字结果
            res = res * 10 + sign * tmp;
            index++;

        }



        return res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("-91283472332"));
    }
}
