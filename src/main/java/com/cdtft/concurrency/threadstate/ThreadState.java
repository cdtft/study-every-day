package com.cdtft.concurrency.threadstate;

/**
 * 使用Jps找到进程id。
 * 使用jstack查看现场状态。
 *
 */
public class ThreadState {

    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }

    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class Blocking implements Runnable {

        @Override
        public void run() {
            synchronized (Blocking.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "timeWaiting").start();
        new Thread(new Waiting(), "waiting").start();
        new Thread(new Blocking(), "blocking-0").start();
        new Thread(new Blocking(), "blocking-1").start();
    }

}
