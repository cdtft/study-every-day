package com.cdtft.concurrency.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangcheng
 * @date: 2021年02月12 11:01
 */
public class BoundedQueue<T> {

    private Object[] items;

    private int headIndex;

    private int tailIndex;

    private int count;

    private Lock lock = new ReentrantLock();

    private final Condition removeCondition = lock.newCondition();

    private final Condition addCondition = lock.newCondition();

    public BoundedQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must large than zero!");
        }
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            //如果队列已经满了则加入【Full】的等待队列,在队列还有空间的时候唤醒
            while (count == items.length) {
                addCondition.await();
            }
            items[headIndex] = t;
            if (++headIndex == items.length) {
                headIndex = 0;
            }
            count++;
            //唤醒处于空条件等待队列的线程
            removeCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    private void remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                removeCondition.await();
            }
            items[tailIndex] = null;
            if (++tailIndex == items.length) {
                tailIndex = 0;
            }
            count--;
            addCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
