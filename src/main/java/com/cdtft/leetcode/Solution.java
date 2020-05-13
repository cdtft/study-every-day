package com.cdtft.leetcode;

import javax.xml.soap.Node;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;

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
        while (indexB >= 0) {
            A[index--] = B[indexB--];
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
        if (nums.length == 1) {
            return nums[0];
        }
        int preMax = maxSubArray_1(Arrays.copyOf(nums, nums.length - 1));
        int currentMax = Math.max(preMax + nums[nums.length - 1], preMax);
        return Math.max(currentMax, nums[nums.length - 1]);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getL() == null && root.getR() == null) {
            return 1;
        }
        return 1 + Math.max(maxDepth(root.getL()), maxDepth(root.getR()));
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getR() == null && root.getL() == null) {
            return 1;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int level = 0;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int cur = 0; cur < size; cur++) {
                TreeNode node = list.poll();
                if (node.getL() != null) {
                    list.offer(node.getL());
                }
                if (node.getR() != null) {
                    list.offer(node.getR());
                }
            }
            level++;
        }
        return level;
    }

    public int maxProfit(int[] prices) {
        Integer maxProfit = 0;
        Integer minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit = maxProfit + prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int preIdx = 0;
        int lastIndex = chars.length - 1;
        while (preIdx < lastIndex) {
            boolean preIsLetter = chars[preIdx] >= 'a' && chars[preIdx] <= 'z';
            boolean preIsNum = chars[preIdx] >= '0' && chars[preIdx] <= '9';
            if (!(preIsLetter || preIsNum)) {
                preIdx++;
                continue;
            }
            boolean lastIsLetter = chars[lastIndex] >= 'a' && chars[lastIndex] <= 'z';
            boolean lastIsNum = chars[lastIndex] >= '0' && chars[lastIndex] <= '9';
            if (!(lastIsLetter || lastIsNum)) {
                lastIndex--;
                continue;
            }
            if (chars[preIdx] != chars[lastIndex]) {
                return false;
            }
            preIdx++;
            lastIndex--;
        }
        return true;
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= nums[i];
        }
        return result;
    }

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 摩尔投票
     *
     * @param nums
     * @return
     */
    public int majorityElement_1(int[] nums) {
        int cand = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length - 1; i++) {
            if (cand == nums[i]) {
                count++;
            } else if (--count == 0) {
                cand = nums[i];
                cand = 1;
            }
        }
        return cand;
    }

    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };

    public int reverseBits(int n) {
        int i = 0;
        for (int j = 0; j < 32; j++) {
            i = (i << 1) + (n & 1);
            n = n >> 1;
        }
        return i;
    }

    public int rob(int[] nums) {
        int dp0 = 0;
        int dp1 = 0;
        for (int i = 2; i < nums.length + 2; i++) {
            int temp = Math.max(dp0 + nums[i - 2], dp1);
            dp0 = dp1;
            dp1 = temp;
        }
        return dp1;
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode index = head;
        while (!Objects.isNull(index.next)) {
            if (index.next.val == val) {
                index.next = index.next.next;
                continue;
            }
            index = index.next;
            if (index == null) {
                return head;
            }
        }
        return head;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(nums[i]);
            if (index != null && i - index <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                if (node.getL() != null) {
                    list.offer(node.getL());
                }
                if (node.getR() != null) {
                    list.offer(node.getR());
                }
                TreeNode temp = node.getL();
                node.setL(node.getR());
                node.setR(temp);
            }
        }
        return root;
    }

    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while(num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 5 == 0) {
            num = num % 5;
        }
        if (num == 1) {
            return true;
        }
        return false;
    }

    public void moveZeroes(int[] nums) {
        int preIndex = 0;
        int sufIndex = 0;
        while (preIndex < nums.length) {
            if (nums[preIndex] != 0) {
                nums[sufIndex] = nums[preIndex];
                nums[preIndex] = 0;
                sufIndex++;
            }
            preIndex++;
        }
    }

}