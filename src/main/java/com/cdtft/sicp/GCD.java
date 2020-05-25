package com.cdtft.sicp;

/**
 * 求最大公约数
 * 线性递归
 * 思想：求a和b的最大公约数，如果 a/b 余 c,那么b和c的最大公约数也是a和b的最大公约数
 *
 * @author : wangcheng
 * @date : 2020年05月25日 14:59
 */
public class GCD {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(5, 3));
    }
}
