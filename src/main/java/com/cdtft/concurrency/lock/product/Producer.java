package com.cdtft.concurrency.lock.product;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 17:20
 */
public class Producer implements Runnable {

    private List<Integer> container;

    private final Object producerLock;

    private final Object consumerLock;

    public Producer(List<Integer> container, Object producerLock, Object consumerLock) {
        this.container = container;
        this.producerLock = producerLock;
        this.consumerLock = consumerLock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (producerLock) {
                if (container.size() == 10) {
                    try {
                        producerLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                container.add(1);
                consumerLock.notify();
            }
        }
    }
}
