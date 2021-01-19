package com.cdtft.datastructures.sort;

/**
 * 对排序, 使用数组表示二叉堆
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2021年01月12日 16:55
 */
public class Heap {

    protected final Comparable[] pq;

    protected int N = 0;

    public Heap(int N) {
        this.pq = new Comparable[N + 1];
    }

    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Comparable temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 由下至上的堆的有序化
     *
     * @param k
     */
    protected void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
        }
    }

    /**
     * 由上至下堆的有序化
     *
     * @param k
     */
    protected void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            //找出 2K位置小还是2k+1位置小, 让较大的节点和父节点进行交换
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (less(k, j)) {
                exch(k, j);
            }
        }
    }

    protected Comparable get(int index) {
        return pq[index];
    }

}
