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
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};
        solution.merge2(a, 3, b, 3);
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
}