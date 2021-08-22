package com.cdtft.springframework.beans.factory.config;

import com.cdtft.springframework.beans.PropertyValues;

/**
 * @author: wangcheng
 * @date: 2021年08月02 16:44
 */
public class BeanDefinition {

    private final Class<?> beanClass;

    private final PropertyValues propertyValues;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

}
