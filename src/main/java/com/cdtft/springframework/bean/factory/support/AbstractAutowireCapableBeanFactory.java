package com.cdtft.springframework.bean.factory.support;

import com.cdtft.springframework.bean.BeansException;
import com.cdtft.springframework.bean.factory.config.BeanDefinition;

/**
 * Capable 有能力的
 *
 * @author: wangcheng
 * @date: 2021年08月03 14:13
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("创建bean失败", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
