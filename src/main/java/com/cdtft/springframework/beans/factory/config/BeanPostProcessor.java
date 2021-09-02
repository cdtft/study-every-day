package com.cdtft.springframework.beans.factory.config;

import com.cdtft.springframework.beans.BeansException;

/**
 * 作用对象Bean
 *
 * @author: wangcheng
 * @date: 2021年09月02 14:04
 */
public interface BeanPostProcessor {

    /**
     * bean初始化之前的执行方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * bean初始化后之前的执行方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
