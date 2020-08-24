package com.cdtft.concurrency.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : wangcheng
 * @date : 2020年08月24日 11:01
 */
public class PrintABC {

    private ReentrantLock lock = new ReentrantLock();

    private final Condition printACondition = lock.newCondition();

    private final Condition printBCondition = lock.newCondition();

    private final Condition printCCondition = lock.newCondition();

    private volatile boolean isA = true;

    private volatile boolean isB = false;

    private volatile boolean isC = false;

    public void print() {
        new Thread(new ThreadPrintA(), "A-1").start();
        new Thread(new ThreadPrintA(), "A-2").start();
        new Thread(new ThreadPrintB(), "B-1").start();
        new Thread(new ThreadPrintB(), "B-2").start();
        new Thread(new ThreadPrintC(), "C-1").start();
        new Thread(new ThreadPrintC(), "C-2").start();
    }

    class ThreadPrintA implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (!isA) {
                        printACondition.await();
                    }
                    System.out.println("A");
                    isA = false;
                    isB = true;
                    isC = false;
                    Thread.sleep(1000);
                    printBCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class ThreadPrintB implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (!isB) {
                        printBCondition.await();
                    }
                    isA = false;
                    isB = false;
                    isC = true;
                    Thread.sleep(1000);
                    System.out.println("B");
                    printCCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class ThreadPrintC implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (!isC) {
                        printCCondition.await();
                    }
                    isA = true;
                    isB = false;
                    isC = false;
                    Thread.sleep(1000);
                    System.out.println("C");
                    printACondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintABC printer = new PrintABC();
        printer.print();
    }

}
