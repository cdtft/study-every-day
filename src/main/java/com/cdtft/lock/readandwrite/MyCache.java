package com.cdtft.lock.readandwrite;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : wangcheng
 * @date : 2020年04月01日 09:45
 */
public class MyCache {

    private static final HashMap<String, String> cache = new HashMap<>();

    static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static final Lock readLock = readWriteLock.readLock();

}
