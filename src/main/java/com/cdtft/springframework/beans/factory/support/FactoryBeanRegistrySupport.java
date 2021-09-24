package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wangcheng
 * @date: 2021年09月24 09:50
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /**
     * 缓存通过FactoryBean创建的singleton
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        return this.factoryBeanObjectCache.get(beanName);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object bean = factoryBeanObjectCache.get(beanName);
            if (bean == null) {
                bean = doGetObjectFromFactoryBean(factory, beanName);
                factoryBeanObjectCache.put(beanName, bean);
            }
            return bean;
        }
        return doGetObjectFromFactoryBean(factory, beanName);
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean, String beanName) {
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("factory bean fail to create [" + beanName + "]");
        }
    }

}
