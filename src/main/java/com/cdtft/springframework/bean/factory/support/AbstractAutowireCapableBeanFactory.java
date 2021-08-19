package com.cdtft.springframework.bean.factory.support;

import com.cdtft.springframework.bean.BeansException;
import com.cdtft.springframework.bean.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Capable 有能力的
 *
 * @author: wangcheng
 * @date: 2021年08月03 14:13
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

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

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        return createBeanInstance(beanName, beanDefinition, args);
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> constructor = null;
        Class<?> clazz = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            //构造参数的长度和传参保持一直
            if (ctor != null && ctor.getParameterTypes().length == args.length) {
                constructor = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
    }
}
