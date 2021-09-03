package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeanReference;
import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.PropertyValue;
import com.cdtft.springframework.beans.PropertyValues;
import com.cdtft.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.beans.factory.config.BeanPostProcessor;
import com.cdtft.springframework.util.BeanUtil;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Capable 有能力的
 *
 * @author: wangcheng
 * @date: 2021年08月03 14:13
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private final InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return createBean(beanName, beanDefinition, null);
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);

            applyBeanPropertyValues(beanName, bean, beanDefinition);

            initializeBean(beanName, bean, beanDefinition);

        } catch (Exception e) {
            throw new BeansException("Instantiation if bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor<?> constructor = null;
        Class<?> clazz = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        if (args != null) {
            for (Constructor<?> ctor : declaredConstructors) {
                //构造参数的长度和传参保持一直
                if (ctor != null && ctor.getParameterTypes().length == args.length) {
                    constructor = ctor;
                    break;
                }
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        invokeInitMethods(beanName, bean, beanDefinition);

        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    @Override
    public void applyBeanPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                //字段名称
                String fieldName = propertyValue.getFieldName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }
                BeanUtil.setFieldValue(bean, fieldName, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values:" + beanName);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object bean = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor processor : beanPostProcessors) {
            processor.postProcessBeforeInitialization(bean, beanName);
        }
        return bean;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object bean = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor processor : beanPostProcessors) {
            processor.postProcessAfterInitialization(bean, beanName);
        }
        return bean;
    }
}
