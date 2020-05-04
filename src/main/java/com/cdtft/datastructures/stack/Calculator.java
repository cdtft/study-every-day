package com.cdtft.datastructures.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 把一个中缀表达式转成一个后缀表达式
 * a+b*c+(d*e+f)*g => abc*+de*f+g*+
 * 转成后缀表达式后直接压栈操作，不用考虑操作的的优先级
 */
public class Calculator {

    private static final Map<Character, Integer> weightsChar = new HashMap<>();

    public Calculator() {
        this.initWeighCharMap();
    }

    private void initWeighCharMap() {
        weightsChar.put('+', 0);
        weightsChar.put('-', 0);
        weightsChar.put('*', 1);
        weightsChar.put('/', 1);
        weightsChar.put('(', 2);
        weightsChar.put(')', 2);
    }

    public char[] preToMiddle(Stack<Character> expression) {
        Stack<Character> op = new Stack<>();
        char[] middleExpression = new char[expression.size()];
        int index = 0;
        while (expression.empty()) {
            Character c = expression.pop();
            if (weightsChar.containsKey(c)) {
                if (op.isEmpty()) {
                    op.push(c);
                } else {
                    if (c == '(') {
                        op.push(c);
                        continue;
                    }
                    if (c == ')') {
                        while (op.peek() != ')') {
                            middleExpression[index++] = op.pop();
                        }
                        op.pop();
                        continue;
                    }
                    Character topChar = op.peek();
                    if (weightsChar.get(topChar) < weightsChar.get(c)) {
                        op.push(c);
                    } else if (weightsChar.get(topChar) == weightsChar.get(c)) {
                        middleExpression[index++] = op.pop();
                        op.push(c);
                    } else if (weightsChar.get(topChar) > weightsChar.get(c)) {
                        while (weightsChar.get(topChar) > weightsChar.get(c)) {
                            middleExpression[index++] = op.pop();
                        }
                        op.push(c);
                    }
                }
            } else {
                middleExpression[index++] = c;
            }
        }
        return middleExpression;
    }
}
