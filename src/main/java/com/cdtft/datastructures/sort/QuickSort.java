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
        if (leftPoint >= rightPoint) {
            return;
        }
        int middle = partition(leftPoint, rightPoint, data);
        quickSort(leftPoint, middle -1, data);
        quickSort(middle + 1, rightPoint, data);
    }


    private static int partition(int leftPoint, int rightPoint, int[] data) {
        int leftMaxIdx = leftPoint;
        int rightMaxIdx = rightPoint;
        int pivotPoint = rightPoint;
        int pivot = data[pivotPoint];
        rightPoint--;
        while (true) {
            while(data[leftPoint] < pivot && leftPoint < rightMaxIdx) {
                leftPoint++;
            }
            while (data[rightPoint] > pivot && rightPoint > leftMaxIdx) {
                rightPoint--;
            }
            if (leftPoint >= rightPoint) {
                break;
            }
            swap(rightPoint, leftPoint, data);
        }
        swap(leftPoint, pivotPoint, data);
        return leftPoint;
    }

    private static void swap(int leftPoint, int rightPoint, int[] data) {
        int temp = data[leftPoint];
        data[leftPoint] = data[rightPoint];
        data[rightPoint] = temp;
    }

}
