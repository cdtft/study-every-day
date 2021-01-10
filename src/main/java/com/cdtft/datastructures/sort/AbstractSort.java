package com.cdtft.datastructures.sort;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月15日 10:10
 */
public abstract class AbstractSort implements Sort {

    @Override
    public boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "] = " + a[i]);
        }
    }

    @Override
    public boolean isSorted(Comparable[] a) {
        if (a.length <= 1) {
            return true;
        }
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void exchange(Comparable[] a, int currentIdx, int minIdx) {
        Comparable temp = a[currentIdx];
        a[currentIdx] = a[minIdx];
        a[minIdx] = temp;
    }
}
