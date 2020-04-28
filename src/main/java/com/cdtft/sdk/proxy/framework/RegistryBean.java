package com.cdtft.sdk.proxy.framework;

import java.lang.reflect.Proxy;

/**
 * @author : wangcheng
 * @date : 2020年04月28日 16:22
 */
public class RegistryBean extends ApplicationContext {

    public RegistryBean() {
        this.registry();
    }

    private void registry() {
        String[] className = new String[]{"User"};
        for (String name : className) {
            Class<?> clazz = null;
            Object bean = null;
            try {
                clazz = Class.forName(name);
                Object instance = clazz.newInstance();
                bean = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new CustomInvocationHandler(instance));

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            if (bean != null) {
                container.put(name, bean);
            }
        }
    }

}
