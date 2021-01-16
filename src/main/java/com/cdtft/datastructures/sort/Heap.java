package com.cdtft.datastructures.sort;

/**
 * 对排序, 使用数组表示二叉堆
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2021年01月12日 16:55
 */
public class Heap {

    private final Comparable[] pq;

    public Heap(int N) {
        pq = new Comparable[N];
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Comparable temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

}
