package com.cdtft.performace;

import com.cdtft.datastructures.sum.Sum;

/**
 * @author: wangcheng
 * @date: 2022年04月20 10:30
 */
public class SkipCalculation {


    public static void main(String[] args) {
        int l;
        long then = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
             l = Sum.fib(20);
        }
        long now = System.currentTimeMillis();
        System.out.println("first execute time " + (now - then));

        long then1 = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            l = Sum.fib(20);
        }
        long now1 = System.currentTimeMillis();
        System.out.println("second execute time " + (now1 - then1));
    }
}
