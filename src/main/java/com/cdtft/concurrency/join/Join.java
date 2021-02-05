package com.cdtft.concurrency.join;

import com.cdtft.concurrency.threadstate.SleepUtils;

/**
 * @author: wangcheng
 * @date: 2021年02月05 17:24
 */
public class Join {

    static class Domino implements Runnable {

        private Thread preThread;

        public Domino(Thread preThread) {
            this.preThread = preThread;
        }

        @Override
        public void run() {
            try {
                preThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "terminate");
        }
    }

    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName() + "terminate");
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(currentThread), String.valueOf(i));
            thread.start();
            currentThread = thread;
        }
        SleepUtils.second(5);
        System.out.println(currentThread.getName() + "terminate");
    }
}
