package org.ivan.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：
 *
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-queue-using-stacks
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2022−09-18 11:25
 **/
public class MyQueueOn {
    private int front;
    private Deque<Integer> inStack = new LinkedList<>();
    private Deque<Integer> outStack = new LinkedList<>();
    public MyQueueOn() {

    }

    /**
     * 直接入栈
     * @param x
     */
    public void push(int x) {
        if (inStack.isEmpty()) {
            front = x;
        }
        inStack.push(x);
    }

    /**
     * 出的时候将入栈的栈数据push进去
     * @return
     */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public int peek() {
        if (!outStack.isEmpty()){
            return outStack.peek();
        }
        return front;
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
