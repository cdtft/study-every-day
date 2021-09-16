package com.cdtft.springframework.beans.factory;

/**
 * @author: wangcheng
 * @date: 2021年09月15 15:25
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassloader(ClassLoader classloader);

}
