package com.cdtft.concurrency.lock.twinslock;

import com.cdtft.concurrency.threadstate.SleepUtils;

/**
 * @author: wangcheng
 * @date: 2021年02月10 22:30
 */
public class TwinsLockTest {

    public static void main(String[] args) {

        TwinsLock lock = new TwinsLock();

        for (int i = 0; i < 10; i++) {
            Work work = new Work(lock);
            work.setDaemon(true);
            work.start();
        }

        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }

    static class Work extends Thread {

        private final TwinsLock twinsLock;

        public Work(TwinsLock twinsLock) {
            this.twinsLock = twinsLock;
        }

        @Override
        public void run() {
            while (true) {
                twinsLock.lock();
                try {
                    SleepUtils.second(1);
                    System.out.println("thread " + Thread.currentThread().getName() + "get lock");
                    SleepUtils.second(1);
                } finally {
                    twinsLock.unlock();
                }

            }
        }
    }

}
