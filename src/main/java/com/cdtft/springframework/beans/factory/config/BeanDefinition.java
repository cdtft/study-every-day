package com.cdtft.springframework.beans.factory.config;

/**
 * @author: wangcheng
 * @date: 2021年08月02 16:44
 */
public class BeanDefinition {

    private final Class<?> beanClass;

    public BeanDefinition (Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
