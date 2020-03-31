package com.cdtft.lock.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 11:31
 */
public class ThreadB implements Runnable {

    private ReentrantLock lockA;

    private ReentrantLock lockB;

    public ThreadB(ReentrantLock lockA, ReentrantLock lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        lockB.lock();
        System.out.println("获取锁B");
        lockA.lock();
        lockA.unlock();
        lockB.unlock();
    }
}
