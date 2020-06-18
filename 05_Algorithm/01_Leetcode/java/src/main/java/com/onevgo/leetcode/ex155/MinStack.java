package com.onevgo.leetcode.ex155;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    private Deque<Integer> data = new ArrayDeque<>();
    private Deque<Integer> minData = new ArrayDeque<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        Integer min = minData.peek();
        if (min == null || x < min) {
            minData.push(x);
        } else {
            minData.push(min);
        }
        data.push(x);
    }

    public void pop() {
        if (!minData.isEmpty()) {
            minData.pop();
            data.pop();
        }
    }

    public int top() {
        Integer top = data.peek();
        return top == null ? 0 : top;
    }

    public int getMin() {
        Integer min = minData.peek();
        return min == null ? 0 : min;
    }
}
