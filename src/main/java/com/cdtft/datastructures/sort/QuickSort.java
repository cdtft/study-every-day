package com.cdtft.datastructures.sort;

import java.util.Arrays;

/**
 * @author: wangcheng
 * @date: 2021年11月22 15:50
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] data = new int[]{5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(data));
        quickSort(0, data.length - 1, data);
        System.out.println(Arrays.toString(data));
    }

    public static void quickSort(int leftPoint, int rightPoint, int[] data) {
        if (leftPoint > rightPoint) {
            return;
        }
        int partition = partition(leftPoint, rightPoint, data);
        quickSort(leftPoint, partition - 1, data);
        quickSort(partition + 1, rightPoint, data);
    }


    private static int partition(int leftPoint, int rightPoint, int[] data) {
        int originLeftIdx = leftPoint;
        int originRightIdx = rightPoint;
        int pivot = rightPoint;
        int pivotData = data[pivot];
        rightPoint--;
        while (true) {
            while (data[rightPoint] > pivotData && rightPoint > originLeftIdx) {
                rightPoint--;
            }
            while (data[leftPoint] < pivotData && leftPoint < originRightIdx) {
                leftPoint++;
            }
            if (leftPoint >= rightPoint) {
                break;
            }
            swap(leftPoint, rightPoint, data);
        }

        //为什么要交换标准值和leftPoint所指向的值
        //这是应为标准值值取的是最右边的值的原因
        //left指针最后会指向比基准值大的一个值
        swap(pivot, leftPoint, data);

        return leftPoint;
    }

    private static void swap(int leftPoint, int rightPoint, int[] data) {
        int temp = data[leftPoint];
        data[leftPoint] = data[rightPoint];
        data[rightPoint] = temp;
    }

}
