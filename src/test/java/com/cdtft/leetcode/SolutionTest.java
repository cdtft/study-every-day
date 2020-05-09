package com.cdtft.leetcode;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SolutionTest {

    private Solution solution;

    @Before
    public void init() {
        solution = new Solution();
    }

    @Test
    public void towSum() {
        int[] testData = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(solution.towSum(testData, target)));
    }

    @Test
    public void reverseList() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);

        ListNode reverseNode = solution.reverseList(node);
        System.out.println(reverseNode.val);
    }

    @Test
    public void merge2() {
        int[] a = {2, 0};
        int[] b = {1};
        solution.merge2(a, 1, b, 1);
    }

    @Test
    public void isValid() {
        assert solution.isValid("]]") == false;
    }

    @Test
    public void mergeTwoLists() {
        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode node = solution.mergeTwoLists(node1, node2);
        printNode(node);
    }

    private void printNode(ListNode node) {
        if (node.next == null) {
            return;
        }
        printNode(node.next);
        System.out.println(node.val);
    }

    @Test
    public void removeDuplicates() {
        int[] num = new int[]{1, 1, 2, 2, 5};
        System.out.println(solution.removeDuplicates(num));
    }

    @Test
    public void bSearch() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        System.out.println(solution.bSearch(nums, 0, nums.length, 2));
    }

    @Test
    public void fib() {
        System.out.println(solution.fib(5));
    }

    @Test
    public void maxSubArray() {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray_1(array));
    }

    @Test
    public void maxDepth() {
        TreeNode root = new TreeNode(3);
        root.setL(new TreeNode(9));
        TreeNode node20 = new TreeNode(20);
        node20.setL(new TreeNode(15));
        node20.setR(new TreeNode(7));
        root.setR(node20);
        System.out.println(solution.maxDepth1(root));
    }

    @Test
    public void maxProfit() {
        //[7,1,5,3,6,4]
        int[] array = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(solution.maxProfit(array));
    }
}