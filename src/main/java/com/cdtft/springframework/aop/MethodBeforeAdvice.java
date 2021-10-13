package com.cdtft.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author: wangcheng
 * @date: 2021年10月12 13:56
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * callback before a given methods is invoked
     *
     * @param method
     * @param args
     * @param o
     * @throws Throwable
     */
    void before(Method method, Object[] args, Object o) throws Throwable;

}
