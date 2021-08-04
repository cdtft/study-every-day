package com.cdtft.springframework.bean.factory.support;

import com.cdtft.springframework.bean.BeansException;
import com.cdtft.springframework.bean.factory.BeanFactory;
import com.cdtft.springframework.bean.factory.config.BeanDefinition;
import com.cdtft.springframework.bean.factory.config.DefaultSingletonBeanRegistry;

/**
 * @author: wangcheng
 * @date: 2021年08月03 13:50
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String className) {
        Object bean = getSingleton(className);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(className);
        return createBean(className, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
