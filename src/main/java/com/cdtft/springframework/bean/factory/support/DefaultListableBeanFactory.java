package com.cdtft.springframework.bean.factory.support;

import com.cdtft.springframework.bean.BeansException;
import com.cdtft.springframework.bean.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年08月04 14:59
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

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
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

}
