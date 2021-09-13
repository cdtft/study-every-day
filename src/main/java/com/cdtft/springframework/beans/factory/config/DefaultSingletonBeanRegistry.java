package com.cdtft.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.cdtft.springframework.beans.factory.DisposableBean;

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

    protected void addSingleton(String beanName, Object singleObject) {
        singletonObjects.put(beanName, singleObject);
    }

    public void registerDisposableBean(String beanName, Object bean) {
        synchronized (this.disposableBeans) {
            disposableBeans.put(beanName, bean);
        }
    }

    public void destroySingletons() {
        Set<String> strings = disposableBeans.keySet();
        //倒序remove
        Object[] beanNames = strings.toArray();
        for (int i = beanNames.length - 1; i > 0; i--) {
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
