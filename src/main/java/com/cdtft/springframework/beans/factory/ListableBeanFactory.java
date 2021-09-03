package com.cdtft.springframework.beans.factory;

import com.cdtft.springframework.beans.BeansException;

import java.util.Map;

/**
 * 科枚举所有的bean实例
 *
 * @author: wangcheng
 * @date: 2021年09月02 14:34
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 更具bean的类型获取bean实例
     *
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException;

    /**
     * 获取注册表总所有的bean名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();

}
