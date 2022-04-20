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

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static int fib1(int n) {
        int i = 0;
        int fn1 = 0;
        int fn2 = 1;
        while (i++ < n) {
            fn2 = fn2 + fn1;
            //等于上一个fn2
            fn1 = fn2 - fn1;
//            System.out.println("fn(" + i + ") = " + fn1);
        }
        return fn2;
    }


}
