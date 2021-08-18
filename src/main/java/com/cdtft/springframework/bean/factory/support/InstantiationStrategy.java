package com.cdtft.springframework.bean.factory.support;

import com.cdtft.springframework.bean.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author: wangcheng
 * @date: 2021年08月18 16:10
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object[] args);

}
