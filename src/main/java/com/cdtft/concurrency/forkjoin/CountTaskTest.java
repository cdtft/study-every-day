package com.cdtft.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @author: wangcheng
 * @date: 2021年02月13 22:41
 */
public class CountTaskTest {

    public static void main(String[] args) {
        CountTask task = new CountTask(1, 10);
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
