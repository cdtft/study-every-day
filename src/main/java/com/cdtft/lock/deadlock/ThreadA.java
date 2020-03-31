package com.cdtft.lock.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 11:25
 */
public class ThreadA implements Runnable {

    private ReentrantLock lockA;

    private ReentrantLock lockB;

    public ThreadA(ReentrantLock lockA, ReentrantLock lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        lockA.lock();
        System.out.println("获取A ReentrantLock");
        lockB.lock();
        System.out.println("获取B ReentrantLock");
        lockB.unlock();
        lockA.unlock();
    }
}
