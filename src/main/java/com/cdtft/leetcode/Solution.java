package com.cdtft.leetcode;

import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wangcheng
 * @date : 2020年02月28日 12:11
 */
public class Solution {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * <p>
     * 示例:
     * <p>
     * 给定 nums = [2, 7, 11, 15], target = 9
     * <p>
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] towSum(int[] nums, int target) {
        Map<Integer, Integer> valueAndIndexMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            valueAndIndexMap.put(nums[i], i);
        }
        int[] returnArray = new int[2];
        for (int j = 0; j < nums.length; j++) {
            Integer index = valueAndIndexMap.get(target - nums[j]);
            if (index != null && j != index) {
                returnArray[0] = j;
                returnArray[1] = index;
            }
        }
        return returnArray;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * <p>
     * 输入: 120
     * 输出: 21
     * 注意:
     * <p>
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long reverseNum = 0;
        while (x != 0) {
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }
        return (int) reverseNum == reverseNum ? (int) reverseNum : 0;
    }

    public int romanToInt(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);
        int romanInt = 0;
        for (int i = 0; i <= s.length()-1; i++) {

            if (i == s.length()-1) {
                romanInt = romanInt + charMap.get(s.charAt(i));
                break;
            }
            char currentChar = s.charAt(i);
            char nextChar = s.charAt(i + 1);

            if (currentChar == 'I') {
                if (nextChar == 'V' || nextChar == 'X') {
                    romanInt = romanInt - charMap.get(currentChar);
                    continue;
                }
            }
            if (currentChar == 'X') {
                if (nextChar == 'L' || nextChar == 'C') {
                    romanInt = romanInt - charMap.get(currentChar);
                    continue;
                }
            }
            if (currentChar == 'C') {
                if (nextChar == 'D' || nextChar == 'M') {
                    romanInt = romanInt - charMap.get(currentChar);
                    continue;
                }
            }
            romanInt = romanInt + charMap.get(currentChar);
        }
        return romanInt;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode nextNode = cur.next;
        cur.next = pre;
        return reverse(cur, nextNode);
    }

    public static void main(String[] args) {
       // ListNode node1 = new ListNode();
    }

}
