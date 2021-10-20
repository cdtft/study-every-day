package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供了注册bean的能力
 *
 * @author: wangcheng
 * @date: 2021年08月04 14:59
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private static final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("no bean named " + beanName + "is defined");
        }
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {

    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> requiredType) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                result.put(beanName, requiredType.cast(getBean(beanName)));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        Map<String, T> beansOfType = getBeansOfType(clazz);
        if (beansOfType == null || beansOfType.isEmpty()) {
            throw new BeansException("no bean match required type :" + clazz.getName());
        }
        if (beansOfType.size() > 1) {
            throw new BeansException("required type " + clazz.getName() + " expected single matching bean but found " + beansOfType.size());
        }
        return beansOfType.values().stream().findFirst().get();
    }
}
