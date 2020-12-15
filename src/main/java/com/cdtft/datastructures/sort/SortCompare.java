package com.cdtft.datastructures.sort;

import com.google.common.base.Stopwatch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 算法比较工具
 *
 * @author : 努力学习JAVA的wangcheng
 * @date : 2020年12月15日 16:17
 */
public class SortCompare {

    public static long time(String algType, Double[] a) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        switch (algType) {
            case "insertion":
                Insertion insertion = new Insertion();
                insertion.sort(a);
                break;
            case "selection":
                Selection selection = new Selection();
                selection.sort(a);
                break;
        }
        return stopwatch.elapsed(TimeUnit.MILLISECONDS);
    }

    public static long timeRandomInput(String algType, int size, int times) {
        long total = 0L;
        Double[] testArray = new Double[size];
        for (int j = 0; j < times; j++) {
            for (int i = 0; i < size; i++) {
                Random random = new Random();
                testArray[i] = random.nextDouble();
            }
            total += time(algType, testArray);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("insertion sort: 100times " + timeRandomInput("insertion", 1000, 100));
        System.out.println("selection sort: 100times " + timeRandomInput("selection", 1000, 100));
    }
}