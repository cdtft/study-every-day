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
}