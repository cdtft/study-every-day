package com.cdtft.leetcode._0150_evaluate_reverse_polish_notation;

import java.util.Stack;

/**
 * @author: wangcheng
 * @date: 2022年10月20 13:54
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(solution.evalRPN(tokens));
        tokens = new String[]{"4", "13", "5", "/", "+"};
        System.out.println(solution.evalRPN(tokens));
        tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(solution.evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (isOp(token)) {
                int first = stack.pop();
                int second = stack.pop();
                stack.push(doOp(second, first, token));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private boolean isOp(String op) {
        return (op.equals("/") || op.equals("+") || op.equals("*") || op.equals("-"));
    }

    private int doOp(int first, int second, String op) {
        if (op.equals("/")) {
            return first / second;
        }
        if (op.equals("*")) {
            return first * second;
        }
        if (op.equals("-")) {
            return first - second;
        }
        if (op.equals("+")) {
            return first + second;
        }
        throw new IllegalArgumentException("错误的操作符");
    }
}
