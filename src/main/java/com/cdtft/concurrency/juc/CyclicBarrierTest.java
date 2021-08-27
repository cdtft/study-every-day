package com.cdtft.concurrency.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: wangcheng
 * @date: 2021年08月25 13:59
 */
public class CyclicBarrierTest {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) {


        Executor executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Print print = new Print(cyclicBarrier);
            executor.execute(print);
        }

    }

    static class Print implements Runnable {

        private final CyclicBarrier cyclicBarrier;

        public Print(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(1000));
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

}
