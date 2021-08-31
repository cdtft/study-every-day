package com.cdtft.springframework.core.io;

import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * 这个接口需要提供的能力：1.获取bean注册器 2.获得资源加载器 3.加载bean定义的方法
 *
 * @author: wangcheng
 * @date: 2021年08月31 10:29
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

}
