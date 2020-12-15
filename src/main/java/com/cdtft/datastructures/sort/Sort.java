package com.cdtft.datastructures.sort;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月15日 10:23
 */
public interface Sort {

    void sort(Comparable[] a);

    void show(Comparable[] a);

    boolean isSorted(Comparable[] a);

    void exchange(Comparable[] a, int currentIdx, int minIdx);

    boolean less(Comparable a, Comparable b);

}
