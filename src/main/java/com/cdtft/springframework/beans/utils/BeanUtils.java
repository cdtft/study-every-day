package com.cdtft.springframework.beans.utils;

import com.cdtft.springframework.beans.BeansException;

import java.lang.reflect.Field;

/**
 * @author: wangcheng
 * @date: 2021年08月22 10:59
 */
public class BeanUtils {

    /**
     * 通过反射设置bean的属性
     *
     * @param bean
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object bean, String fieldName, Object value)  {
        Class<?> clazz = bean.getClass();
        Field declaredFields = null;
        try {
            declaredFields = clazz.getDeclaredField(fieldName);
            declaredFields.setAccessible(true);
            declaredFields.set(bean, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("Fail set Bean field value");
        }
    }

}
