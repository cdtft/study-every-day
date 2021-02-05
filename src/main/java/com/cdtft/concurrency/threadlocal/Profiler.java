package com.cdtft.concurrency.threadlocal;

import com.cdtft.concurrency.threadstate.SleepUtils;

/**
 * 在面向切面编程的时候可以使用该技巧
 *
 * @author: wangcheng
 * @date: 2021年02月05 17:46
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void start() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static Long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    public static void main(String[] args) {
        Profiler.start();
        SleepUtils.second(2);
        System.out.println(Profiler.end());
    }
}
