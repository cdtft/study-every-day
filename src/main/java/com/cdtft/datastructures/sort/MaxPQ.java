package com.cdtft.datastructures.sort;

import java.util.Arrays;

/**
 * 有限队列
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2021年01月12日 16:21
 */
public class MaxPQ extends Heap {


    public MaxPQ(int N) {
        super(N);
    }

    public void insert(Comparable comparable) {
        pq[++N] = comparable;
        swim(N);
    }

    public Comparable delMax() {
        Comparable maxValue = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1);
        return maxValue;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {

        MaxPQ pq = new MaxPQ(5);

        pq.insert(1);
        pq.insert(2);

        System.out.println("delete max value:" + pq.delMax());

        for (int i = 0; i < 6; i++) {
            System.out.println(pq.get(i));
        }
    }

}
