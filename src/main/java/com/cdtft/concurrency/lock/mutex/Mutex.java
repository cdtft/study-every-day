package com.cdtft.concurrency.lock.mutex;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自己实现一个独占锁
 *
 * @author: wangcheng
 * @date: 2021年02月08 09:50
 */
public class Mutex implements Lock {

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 实现状态改变逻辑
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalThreadStateException();
            }
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        for (int i = 0; i < 10; i++) {
            new Thread(mutex.new TestMutex(mutex), String.valueOf((i))).start();
        }
    }

    class TestMutex implements Runnable {

        private final Mutex mutex;

        public TestMutex(Mutex mutex) {
            this.mutex = mutex;
        }

        @Override
        public void run() {
            mutex.lock();
            System.out.println(Thread.currentThread().getName());
            mutex.unlock();
        }
    }

}
