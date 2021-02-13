package com.cdtft.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author: wangcheng
 * @date: 2021年02月13 22:34
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;

    private final int start;

    private final int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (end - start) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            int leftSum = leftTask.join();
            int rightSum = rightTask.join();
            sum = leftSum + rightSum;
        }
        return sum;
    }
}
