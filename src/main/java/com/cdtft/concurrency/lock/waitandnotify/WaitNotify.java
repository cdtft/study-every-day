package com.cdtft.concurrency.lock.waitandnotify;

import com.cdtft.concurrency.threadstate.SleepUtils;

import java.time.LocalDateTime;

/**
 *  Monitor.Enter 失败 --> SynchronizedQueue
 *
 *  Object.wait() --> WaitQueue --> Object.notify --> SynchronizedQueue
 *
 * @author : wangcheng
 * @date : 2020年03月31日 14:12
 */
public class WaitNotify {

    static boolean flag = false;

    static final Object lock = new Object();

    static class WaitRunnable implements Runnable {
        @Override
        public void run() {
            if (!flag) {
                synchronized (lock) {
                    System.out.println(LocalDateTime.now().toString() + "WaitRunnable get lock...");
                    try {
                        lock.wait();
                        System.out.println(LocalDateTime.now().toString() + "WaitRunnable 再次被唤醒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(LocalDateTime.now().toString() + "WaitRunnable execute success");
        }
    }

    static class NotifyRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(LocalDateTime.now().toString() + "NotifyRunnable get lock...");
                lock.notify();
                flag = true;
                SleepUtils.second(5);
                System.out.println(LocalDateTime.now().toString() + "NotifyRunnable 释放锁");
            }
            SleepUtils.second(2);
            synchronized (lock) {
                System.out.println(LocalDateTime.now().toString() + "NotifyRunnable get lock again");
                SleepUtils.second(10);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new WaitRunnable()).start();
        SleepUtils.second(2);
        new Thread(new NotifyRunnable()).start();
    }
}
