package com.cdtft.datastructures.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序的优化算法
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月22日 14:39
 */
public class Shell extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int cap = 1;
        while (cap < N / 3) {
            cap = cap * 3 + 1;
        }
        while (cap >= 1) {
            //i 起始位置是最小为间隙大小
            for (int i = cap; i < N; i++) {
                //对有序数组进行插入排序 从左到右
                for (int j = i; j >= cap; j = j - cap) {
                    if (less(a[j], a[j - cap])) {
                        exchange(a, j, j - cap);
                    }
                }
            }
            cap = cap / 3;
        }
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        Random random = new Random();
        Integer[] testArray = new Integer[5];
        for (int i = 0; i < 5; i++) {
            testArray[i] = random.nextInt();
        }

        shell.sort(testArray);
        shell.show(testArray);
    }
}
