package com.cdtft.springframework.beans.factory.config;

import com.cdtft.springframework.beans.factory.BeanFactory;

/**
 * @author: wangcheng
 * @date: 2021年09月02 16:26
 */
public interface ConfigurableBeanFactory extends SingletonBeanRegistry, BeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

}
