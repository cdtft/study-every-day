package com.cdtft.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.cdtft.springframework.beans.BeansException;
import com.cdtft.springframework.beans.factory.DisposableBean;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: wangcheng
 * @date: 2021年08月03 11:09
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放配置销毁方法的
     */
    private final Map<String, Object> disposableBeans = new LinkedHashMap<>();

    /**
     * 存放已经实例化好了的对象
     */
    private static final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (singletonObjects) {
            Object oldObject = getSingleton(beanName);
            if (oldObject != null) {
                throw new BeansException("Could not register object [" + singletonObject + "] " +
                        "under bean name '" + beanName + "' there is already object [" +oldObject+ "] bound");
            }
            singletonObjects.put(beanName, singletonObject);
        }
    }

    protected void addSingleton(String beanName, Object singleObject) {
        singletonObjects.put(beanName, singleObject);
    }

    public void registerDisposableBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean) {
            synchronized (this.disposableBeans) {
                disposableBeans.put(beanName, bean);
            }
        } else {
            //没有实现DisposableBean接口的但是在配置文件中有销毁方法
            //则新建适配器
            disposableBeans.put(beanName, new DisposableBeanAdapter(bean, beanDefinition.getDestroyMethodName()));
        }
    }

    public void destroySingletons() {
        Set<String> strings = disposableBeans.keySet();
        //倒序remove
        Object[] beanNames = strings.toArray();
        for (int i = beanNames.length - 1; i >= 0; i--) {
            Object beanName = beanNames[i];
            DisposableBean o = (DisposableBean) disposableBeans.remove(beanName.toString());
            try {
                o.destroy();
            } catch (Exception e) {
                throw new BeanException("Destroy method on bean with name" + beanName + "throw exception");
            }
        }
    }

}
