package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.factory.config.BeanDefinition;

/**
 * @author: wangcheng
 * @date: 2021年08月04 15:02
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);


    /**
     * 判断bean是否已经注册
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

}
