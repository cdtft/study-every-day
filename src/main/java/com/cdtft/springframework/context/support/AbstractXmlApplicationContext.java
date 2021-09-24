package com.cdtft.springframework.context.support;

import com.cdtft.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cdtft.springframework.core.io.XmlBeanDefinitionReader;

/**
 * @author: wangcheng
 * @date: 2021年09月02 17:48
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        xmlBeanDefinitionReader.loadBeanDefinitions(getConfigLocation());
    }

    protected abstract String getConfigLocation();

}
