package com.cdtft.concurrency.juc;

import com.cdtft.concurrency.threadstate.SleepUtils;

import java.util.concurrent.Semaphore;

/**
 * @author: wangcheng
 * @date: 2021年02月23 09:27
 */
public class SemaphoreTest {

    static final Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                System.out.println();
            }
            new Thread(new TestRunnable()).start();
        }
    }

    static class TestRunnable implements Runnable {

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ":acquire available" + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SleepUtils.second(5);
            semaphore.release();
        }
    }
}
