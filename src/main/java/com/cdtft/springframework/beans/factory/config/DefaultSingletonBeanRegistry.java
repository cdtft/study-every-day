package com.cdtft.springframework.beans.factory.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年08月03 11:09
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放已经实例化好了的对象
     */
    private static final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singleObject) {
        singletonObjects.put(beanName, singleObject);
    }

}
