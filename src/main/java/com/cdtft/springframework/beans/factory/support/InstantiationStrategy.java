package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author: wangcheng
 * @date: 2021年08月18 16:10
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args);

}
