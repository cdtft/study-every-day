package com.cdtft.classloader.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: wangcheng
 * @date: 2021年02月07 10:56
 */
public class PingPangDriver {

    static class PingPangHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("ping")) {
                System.out.println("ping");
            }
            if (method.getName().equals("pang")) {
                System.out.println("pang");
            }
            return null;
        }
    }

    public static PingPang createPingPang() {
        return (PingPang) Proxy.newProxyInstance(PingPangDriver.class.getClassLoader(), new Class[]{PingPang.class},
                new PingPangHandler());
    }

    public static void main(String[] args) {
        PingPang pingPang = PingPangDriver.createPingPang();
        pingPang.pang();
        pingPang.ping();
    }

}
