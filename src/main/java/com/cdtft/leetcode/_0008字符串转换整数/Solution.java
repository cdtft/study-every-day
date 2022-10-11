package com.cdtft.leetcode._0008字符串转换整数;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2022年10月08 13:58
 * @link <a href="https://leetcode.cn/problems/string-to-integer-atoi/">...</a>
 */
public class Solution {

    public int myAtoi(String s) {
        DFA dfa = new DFA();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            dfa.get(s.charAt(i));
        }
        return (int) (dfa.sign * dfa.ans);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi("+1"));
    }
}


class DFA {
    public long ans = 0;
    public int sign = 1;
    private String state = "start";
    private Map<String, String[]> table = new HashMap(
    ) {
        {
            put("start", new String[]{"start", "singed", "end", "in_number"});
            put("singed", new String[]{"end", "end", "end", "in_number"});
            put("in_number", new String[]{"end", "end", "end", "in_number"});
            put("end", new String[]{"end", "end", "end", "end"});
        }
    };

    public void get(char c) {
        state = table.get(state)[getStateColumNum(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        }
        if ("singed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int getStateColumNum(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c <= '9' && c >= '0') {
            return 3;
        }
        return 2;
    }
}
