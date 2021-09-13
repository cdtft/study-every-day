package com.cdtft.springframework.beans.factory.support;

import com.cdtft.springframework.beans.factory.DisposableBean;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;

/**
 * @author: wangcheng
 * @date: 2021年09月13 11:35
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String destroyName;

    public DisposableBeanAdapter(Object bean, String destroyName) {
        this.bean = bean;
        this.destroyName = destroyName;
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean) {
            DisposableBean disposableBean = (DisposableBean) bean;
            disposableBean.destroy();
        }

        if (StringUtils.isNotBlank(destroyName) && !(bean instanceof DisposableBean && "destroy".equals(destroyName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyName);
            destroyMethod.invoke(bean);
        }
    }
}
