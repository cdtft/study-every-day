package com.cdtft.springframework.beans.factory;

/**
 * @author: wangcheng
 * @date: 2021年09月15 15:41
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}
