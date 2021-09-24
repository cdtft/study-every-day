package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.FactoryBean;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.beans.factory.config.BeanPostProcessor;
import com.cdtft.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangcheng
 * @date: 2021年08月03 13:50
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String className) {
        return getBean(className, (Object[]) null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, args);
    }


    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        Object bean = doGetBean(name, null);

        if (clazz.isInstance(bean)) {
            return clazz.cast(bean);
        }
        throw new BeansException("Fail to cast bean");
    }

    protected <T> T doGetBean(String beanName, Object[] args) {
        Object shareInstance = getSingleton(beanName);
        if (shareInstance != null) {
            return (T) getObjectFromBeanInstance(shareInstance, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);

        return (T) getObjectFromBeanInstance(bean, beanName);
    }

    //主要是判断bean是否是factory bean
    private Object getObjectFromBeanInstance(Object bean, String beanName) {
        if (!(bean instanceof FactoryBean)) {
            return bean;
        }
        Object cachedObjectForFactoryBean = getCachedObjectForFactoryBean(beanName);
        if (cachedObjectForFactoryBean == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
            cachedObjectForFactoryBean = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return cachedObjectForFactoryBean;
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
