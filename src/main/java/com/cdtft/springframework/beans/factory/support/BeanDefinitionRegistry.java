package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.factory.config.BeanDefinition;

/**
 * @author: wangcheng
 * @date: 2021年08月04 15:02
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
