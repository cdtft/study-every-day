package com.cdtft.leetcode._0009_回文数;

/**
 * @author: wangcheng
 * @date: 2022年10月08 11:32
 * @link <a href="https://leetcode.cn/problems/palindrome-number/">...</a>
 */
public class Solution {

    /**
     * 将数字反转后对比和原来的数字是否相同
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        int reverse = 0;
        while (temp != 0) {
            //提取最末尾的数字
            reverse = reverse * 10 + temp % 10;
            //去除最末尾的数字
            temp = temp / 10;
        }
        return x == reverse;
    }
}
