package com.cdtft.springframework.beans.factory.config;

import com.cdtft.springframework.beans.factory.BeanFactory;

/**
 * @author: wangcheng
 * @date: 2021年09月02 16:26
 */
public interface ConfigurableBeanFactory extends SingletonBeanRegistry, BeanFactory {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
