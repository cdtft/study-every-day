package com.cdtft.datastructures.sum;

/**
 * @author: wangcheng
 * @date: 2021年11月12 14:37
 */
public class Sum {

    public static Integer sum(int[] a, int n) {
         if (n == 1) {
             return a[0];
         }
        return sum(a, n - 1) + a[n - 1];
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        System.out.println(sum(a, 3));
    }

}
