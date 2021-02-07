package com.cdtft.concurrency.simplepool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author: wangcheng
 * @date: 2021年02月07 10:42
 */
public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    /**
     * 使用代理方式创建
     *
     * @return
     */
    public static Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionHandler.class.getClassLoader(), new Class[]{Connection.class},
                new ConnectionHandler());
    }
}
