package com.cdtft.springframework.beans.factory.config;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 作用对象BeanFactory
 *
 * @author: wangcheng
 * @date: 2021年09月02 13:57
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载后、实例化bean之前提供对BeanDefinition修改
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
