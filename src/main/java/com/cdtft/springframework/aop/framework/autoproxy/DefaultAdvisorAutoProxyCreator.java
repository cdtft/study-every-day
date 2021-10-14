package com.cdtft.springframework.aop.framework.autoproxy;

import cn.hutool.core.bean.BeanException;
import com.cdtft.springframework.aop.AdvisedSupport;
import com.cdtft.springframework.aop.Advisor;
import com.cdtft.springframework.aop.AspectJExpressionPointcutAdvisor;
import com.cdtft.springframework.aop.Pointcut;
import com.cdtft.springframework.aop.TargetSource;
import com.cdtft.springframework.aop.framework.ProxyFactory;
import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.PropertyValues;
import com.cdtft.springframework.beans.factory.BeanFactory;
import com.cdtft.springframework.beans.factory.BeanFactoryAware;
import com.cdtft.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.cdtft.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: wangcheng
 * @date: 2021年10月12 16:25
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeanException {
        //如果是AOP的基础类直接返回
        if (isAopInfrastructureClass(beanClass)) {
            return null;
        }

        //找到容器中所有的的通知和切入点
        Map<String, AspectJExpressionPointcutAdvisor> beansOfType = defaultListableBeanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : beansOfType.values()) {
            if (!advisor.getPointcut().getClassFilter().matches(beanClass)) {
                continue;
            }
            TargetSource targetSource = null;

            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new BeanException("beanClass new instance error when generate aop targetSource, bean name is " + beanName);
            }
            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setProxyTargetClass(false);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException {
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

    private boolean isAopInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

}
