package com.cdtft.springframework.beans.annotation;

import cn.hutool.core.bean.BeanException;
import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.PropertyValues;
import com.cdtft.springframework.beans.factory.BeanFactory;
import com.cdtft.springframework.beans.factory.BeanFactoryAware;
import com.cdtft.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cdtft.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.cdtft.springframework.core.io.ClassUtils;
import com.cdtft.springframework.stereotype.Component;
import com.cdtft.springframework.util.BeanUtil;

import java.lang.reflect.Field;

/**
 * 实现自动注入
 *
 * @author: wangcheng
 * @date: 2021年10月18 11:27
 */
@Component
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
        Class<?> targetClass = bean.getClass();
        targetClass = ClassUtils.isCglibProxyClass(targetClass) ? targetClass.getSuperclass() : targetClass;

        Field[] declaredField = targetClass.getDeclaredFields();
        //处理@Autowire注解
        for (Field field : declaredField) {
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                Class<?> dependencyClass = field.getType();
                Object dependencyBean = null;
                Qualifier qualifier = field.getAnnotation(Qualifier.class);
                if (qualifier != null) {
                    String dependencyBeanName = qualifier.value();
                    dependencyBean = beanFactory.getBean(dependencyBeanName, dependencyClass);
                } else {
                    dependencyBean = beanFactory.getBean(dependencyClass);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependencyBean);
            }
        }

        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
