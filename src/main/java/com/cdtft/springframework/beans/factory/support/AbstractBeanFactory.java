package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.beans.factory.config.BeanPostProcessor;
import com.cdtft.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.cdtft.springframework.beans.factory.config.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangcheng
 * @date: 2021年08月03 13:50
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String className) {
        return getBean(className, (Object[]) null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }


    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        Object bean = getBean(name);
        if (clazz.isInstance(bean)) {
            return clazz.cast(bean);
        }
        throw new BeansException("Fail to cast bean");
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
