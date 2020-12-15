package com.cdtft.datastructures.sort;

/**
 * 插入排序
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月15日 14:30
 */
public class Insertion extends AbstractSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j++) {
                if (less(a[j], a[j - 1])) {
                    exchange(a, j, j - 1);
                }
            }
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
