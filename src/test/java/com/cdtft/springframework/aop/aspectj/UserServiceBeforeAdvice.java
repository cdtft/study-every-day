package com.cdtft.springframework.aop.aspectj;

import com.cdtft.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: wangcheng
 * @date: 2021年10月13 15:52
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object o) throws Throwable {
        System.out.println("拦截方法:" + method.getName());
    }

}
