package com.cdtft.lock.waitandnotify;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(LocalDateTime.now().toString() + "WaitRunnable 再次被唤醒");
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
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(LocalDateTime.now().toString() + "NotifyRunnable 释放锁");
            }
            synchronized (lock) {
                System.out.println(LocalDateTime.now().toString() + "NotifyRunnable get lock again");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new WaitRunnable()).start();
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new NotifyRunnable()).start();
    }
}
