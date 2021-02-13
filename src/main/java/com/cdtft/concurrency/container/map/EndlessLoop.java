package com.cdtft.concurrency.container.map;

import java.util.HashMap;
import java.util.UUID;

/**
 * HashMap在高并发下出现死循环，导致CPU使用率100%的问题。
 *  在我的电脑上并没有出现死循环的问题，是不是这个问题只是在jdk7
 *  中会出现？
 *
 * @author: wangcheng
 * @date: 2021年02月12 12:45
 */
public class EndlessLoop {

    private static final HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                }, "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
    }

}
