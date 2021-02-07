package com.cdtft.concurrency.waitandnotify;

import com.cdtft.concurrency.threadstate.SleepUtils;

/**
 * @author: wangcheng
 * @date: 2021年02月07 13:44
 */
public class WaitTime {

     static class Waiting implements Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            synchronized (WaitTime.class) {
                try {
                    System.out.println(name + ": 让出CPU时间");
                    WaitTime.class.wait();
                    System.out.println(name + ": wait 被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   static  class Notifying implements  Runnable {

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            synchronized (WaitTime.class)  {
                System.out.println(name + "获取锁");
                SleepUtils.second(10);
                System.out.println(name + "释放锁");
                WaitTime.class.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Waiting(), "wait").start();
        SleepUtils.second(1);
        new Thread(new Notifying(), "notifying").start();
    }
}
