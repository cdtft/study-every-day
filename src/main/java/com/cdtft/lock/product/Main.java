package com.cdtft.lock.product;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author : wangcheng
 * @date : 2020年03月31日 17:17
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> container = Lists.newArrayList();
        Object productLock = new Object();
        Object consumerLock = new Object();
        new Thread(new Producer(container, productLock, consumerLock)).start();
        //new Thread(new Producer(container, productLock, consumerLock)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // new Thread(new Consumer(container, productLock, consumerLock)).start();
        //new Thread(new Consumer(container, productLock, consumerLock)).start();
        new Thread(new Consumer(container, productLock, consumerLock)).start();

    }
}
