package com.cdtft.sdk.java8.function;

/**
 * @author : wangcheng
 * @date : 2020年05月26日 17:04
 */
public class Main {

    private int calculate(Calculate c, int a, int b) {
        return c.calculate(a, b);
    }

    public static void main(String[] args) {

    }

    public int add(int a, int b) {
        return a + b;
    }

    public int test(int a, int b) {
        return calculate(this::add, a, b);
    }
}
