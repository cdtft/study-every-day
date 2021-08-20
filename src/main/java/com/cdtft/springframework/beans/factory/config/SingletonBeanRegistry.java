package com.cdtft.springframework.beans.factory.config;

/**
 * @author: wangcheng
 * @date: 2021年08月02 16:44
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
