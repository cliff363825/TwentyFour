package com.onevgo.leetcode;

import java.util.ArrayDeque;

public class Ex150EvalRPN {
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Integer num1, num2;

        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                num2 = deque.pop();
                num1 = deque.pop();
                deque.push(num1 + num2);
            } else if ("-".equals(tokens[i])) {
                num2 = deque.pop();
                num1 = deque.pop();
                deque.push(num1 - num2);
            } else if ("*".equals(tokens[i])) {
                num2 = deque.pop();
                num1 = deque.pop();
                deque.push(num1 * num2);
            } else if ("/".equals(tokens[i])) {
                num2 = deque.pop();
                num1 = deque.pop();
                deque.push(num1 / num2);
            } else {
                deque.push(Integer.parseInt(tokens[i]));
            }
        }

        return !deque.isEmpty() ? deque.pop() : 0;
    }

    public int evalRPN1(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;

        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    stack[index - 2] += stack[index - 1];
                    index--;
                    break;
                case "-":
                    stack[index - 2] -= stack[index - 1];
                    index--;
                    break;
                case "*":
                    stack[index - 2] *= stack[index - 1];
                    index--;
                    break;
                case "/":
                    stack[index - 2] /= stack[index - 1];
                    index--;
                    break;
                default:
                    stack[index] = Integer.parseInt(tokens[i]);
                    index++;
                    break;
            }
        }
        return stack[0];
    }

    public static void main(String[] args) {

    }
}
