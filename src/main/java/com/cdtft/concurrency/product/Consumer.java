package com.cdtft.concurrency.product;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 17:19
 */
public class Consumer implements Runnable {

    private List<Integer> container;

    private final Object producerLock;

    private final Object consumerLock;

    public Consumer(List<Integer> container, Object producerLock, Object consumerLock) {
        this.container = container;
        this.producerLock = producerLock;
        this.consumerLock = consumerLock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (consumerLock) {
                if (container.size() == 0) {
                    try {
                        consumerLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                container.remove(container.size() - 1);
                System.out.println(Thread.currentThread().getId() + "当前容器size" + container.size());
                producerLock.notify();
            }
        }
    }
}
