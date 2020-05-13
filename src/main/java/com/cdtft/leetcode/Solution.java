package com.cdtft.leetcode;

import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
        for (int i = 0; i <= s.length() - 1; i++) {

            if (i == s.length() - 1) {
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
        //关键就是在于在遍历的时候要有一个假象的null节点。
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

    public void merge(int[] A, int m, int[] B, int n) {
        if (n >= 0) System.arraycopy(B, 0, A, m - 1, n);
        popSort(A);
    }

    private static void popSort(int[] C) {
        for (int i = 0; i < C.length; i++) {
            for (int j = 1; j < C.length; j++) {
                if (C[j - 1] > C[j]) {
                    int swap = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = swap;
                }
            }
        }
    }

    public void merge2(int[] A, int m, int[] B, int n) {
        int index = m + n - 1;
        int indexA = m - 1;
        int indexB = n - 1;
        while (indexA >= 0 && indexB >= 0) {
            if (A[indexA] > B[indexB]) {
                A[index--] = A[indexA--];
            } else {
                A[index--] = B[indexB--];
            }
        }
    }

    public int[] distributeCandies(int candies, int num_people) {
        int num = 1;
        int[] candyNum = new int[num_people];
        while (candies > 0) {
            if (candies <= num) {
                candyNum[(num - 1) % num_people] = candyNum[(num - 1) % num_people] + candies;
                candies = 0;
                break;
            }
            candyNum[(num - 1) % num_people] = candyNum[(num - 1) % num_people] + num;
            candies = candies - num;
            num++;
        }
        return candyNum;
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                char popc = stack.pop();
                if (popc != map.get(c)) {
                    return false;
                }
            }

        }
        if (stack.size() != 0) {
            return false;
        }
        return true;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public int removeDuplicates(int[] nums) {
        int preIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[preIdx] != nums[i]) {
                preIdx++;
                nums[preIdx] = nums[i];
            }
        }
        return ++preIdx;
    }

    public int bSearch(int[] nums, int left, int right, int value) {
        //left > rignt
        if (left > right) {
            return -1;
        }
        int idx = left + (left - right) >> 1;
        if (nums[idx] > value) {
            return bSearch(nums, left, idx - 1, value);
        }
        if (nums[idx] < value) {
            return bSearch(nums, idx + 1, right, value);
        }
        return idx;
    }

    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int maxSum = 0;
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currentSum);
            System.out.println(maxSum);
        }
        return maxSum;
    }

    public int maxSubArray_1(int[] nums) {
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println("");
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int preMax = maxSubArray_1(Arrays.copyOf(nums, length - 1));
        int currentMax_1 = Math.max(preMax + nums[length - 1], preMax);
        int currentMax = Math.max(currentMax_1, nums[length-1]);
        return currentMax;
    }

}