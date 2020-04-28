package com.cdtft.sdk.proxy.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : wangcheng
 * @date : 2020年04月28日 16:39
 */
public class CustomInvocationHandler implements InvocationHandler {

    private final Object bean;

    public CustomInvocationHandler(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(bean, args);
        System.out.println("after");
        return null;
    }
}
