package com.cdtft.datastructures.sort;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月15日 10:24
 */
public class Selection extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[minIdx])) {
                    minIdx = j;
                }
            }
            exchange(a, i, minIdx);
        }
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[]{5, 2, 4, 6, 1};
        Selection selection = new Selection();
        selection.show(test);
        selection.sort(test);
        System.out.println(selection.isSorted(test));
        selection.show(test);
    }
}
