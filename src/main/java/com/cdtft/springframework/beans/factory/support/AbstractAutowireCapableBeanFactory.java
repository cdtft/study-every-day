package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.BeanReference;
import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.PropertyValue;
import com.cdtft.springframework.beans.PropertyValues;
import com.cdtft.springframework.beans.factory.Aware;
import com.cdtft.springframework.beans.factory.BeanFactoryAware;
import com.cdtft.springframework.beans.factory.BeanNameAware;
import com.cdtft.springframework.beans.factory.DisposableBean;
import com.cdtft.springframework.beans.factory.InitializingBean;
import com.cdtft.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.beans.factory.config.BeanPostProcessor;
import com.cdtft.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.cdtft.springframework.util.BeanUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
            //被增强的bean
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            if (bean != null) {
                return bean;
            }

            bean = createBeanInstance(beanName, beanDefinition, args);

            //填充bean的属性前提供修改BeanDefinition中的PropertyValue属性,
            //自动注入也是基于此
            applyBeanPostProcessorBeforeApplyingPropertyValue(beanName, bean, beanDefinition);

            //填充bean属性
            applyBeanPropertyValues(beanName, bean, beanDefinition);

            initializeBean(beanName, bean, beanDefinition);

        } catch (Exception e) {
            throw new BeansException("Instantiation if bean failed", e);
        }

        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        return bean;
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

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        //这里可能就是代理bean
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
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

        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }

            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }


        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try {
            invokeInitMethods(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Fail invoke init method");
        }

        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {
        if (wrappedBean instanceof InitializingBean) {
            InitializingBean initializingBean = (InitializingBean) wrappedBean;
            initializingBean.afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StringUtils.isNotBlank(initMethodName)) {
            //return not null
            Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
            method.invoke(wrappedBean);
        }

    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StringUtils.isNotBlank(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, bean, beanDefinition);
        }
    }

    protected void applyBeanPostProcessorBeforeApplyingPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues propertyValues = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (propertyValues != null) {
                    for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

}
