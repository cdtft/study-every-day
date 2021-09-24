package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Test FactoryBean
 *
 * @author: wangcheng
 * @date: 2021年09月24 14:18
 */
public class ProxyFactoryBean implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> contentMap = new HashMap<>();
            contentMap.put("10001", "小王");
            contentMap.put("10002", "小张");
            contentMap.put("10003", "小李");

            return "this is proxy" + method.getName() + ":" + contentMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
