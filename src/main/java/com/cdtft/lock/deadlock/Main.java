package com.cdtft.lock.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 11:34
 */
public class Main {

    public static void main(String[] args) {
        ReentrantLock a = new ReentrantLock();
        ReentrantLock b = new ReentrantLock();

        ThreadB tb = new ThreadB(a, b);
        ThreadA ta = new ThreadA(a, b);
        Thread myTreadA = new Thread(ta);
        Thread myTreadB = new Thread(tb);
        myTreadA.start();
        myTreadB.start();
    }

}
