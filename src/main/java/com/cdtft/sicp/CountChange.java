package com.cdtft.sicp;

/**
 * 换零钱
 * 树形递归
 * 思想：不使用第一种硬币找零的种类 + 金额减去第一种硬币使用所有硬币找零的种类
 *
 * @author : wangcheng
 * @date : 2020年05月25日 14:48
 */
public class CountChange {

    public static int countChange(int amount) {
        return cc(amount, 5);
    }

    private static int cc(int amount, int kindOfCoins) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0) {
            return 0;
        }
        if (kindOfCoins == 0) {
            return 0;
        }
        return cc(amount, kindOfCoins - 1) + cc(amount - firstDenomination(kindOfCoins), kindOfCoins);
    }

    private static int firstDenomination(int kindOfCoins) {
        if (kindOfCoins == 1) {
            return 1;
        } else if (kindOfCoins == 2) {
            return 5;
        } else if (kindOfCoins == 3) {
            return 10;
        } else if (kindOfCoins == 4) {
            return 25;
        } else if (kindOfCoins == 5) {
            return 50;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countChange(100));
    }
}
