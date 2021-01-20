package com.cdtft.datastructures.sort;

import java.util.Random;

/**
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月24日 16:00
 */
public class Merge extends AbstractSort {

    private Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        mergeSort(a, 0, a.length - 1);
    }

    public void mergeSort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        merge(a, low, mid, high);

    }

    /**
     * 原地并归
     *
     * @param a
     * @param low
     * @param mid
     * @param high
     */
    private void merge(Comparable[] a, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        for (int k = low; k <= high; k++) {
            //使用多出来的进行填充
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > high) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] testArray = new Integer[5];
        for (int i = 0; i < 5; i++) {
            testArray[i] = random.nextInt();
        }
        Merge merge = new Merge();
        merge.sort(testArray);
        merge.show(testArray);
        System.out.println(merge.isSorted(testArray));
    }

}
