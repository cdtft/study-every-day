package com.cdtft.datastructures.lcs;

import java.util.StringJoiner;

/**
 * @author: wangcheng
 * @date: 2021年11月23 16:56
 */
public class LCS {

    public static Result lcs(char[] a, char[] b, int aIdx, int bIdx) {
        if (aIdx == a.length || bIdx == b.length) {
            return new Result();
        }
        if (a[aIdx] == b[bIdx]) {
            Result result = lcs(a, b, aIdx + 1, bIdx + 1);
            result.step = result.step + 1;
            result.cStr = a[aIdx] + result.cStr;
            return result;
        }
        Result left = lcs(a, b, aIdx + 1, bIdx);
        Result right = lcs(a, b, aIdx, bIdx + 1);
        if (left.step > right.step) {
            return left;
        }
        return right;
    }

    public static void main(String[] args) {
        char[] a = "didacticA".toCharArray();
        char[] b = "advantA".toCharArray();
        System.out.println(lcs(a, b, 0, 0));
    }

    static class Result {
        int step = 0;
        String cStr = "";

        @Override
        public String toString() {
            return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                    .add("step=" + step)
                    .add("cStr='" + cStr + "'")
                    .toString();
        }
    }

}
