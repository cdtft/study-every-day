package com.cdtft.springframework.util;

import com.cdtft.springframework.beans.BeansException;

import java.lang.reflect.Field;

/**
 * @author: wangcheng
 * @date: 2021年08月22 10:59
 */
public class BeanUtil {

    /**
     * 通过反射设置bean的属性
     *
     * @param bean
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object bean, String fieldName, Object value)  {
        Class<?> clazz = bean.getClass();
        Object setValue = value;

        Field declaredFields = null;
        try {
            //代理类的父类是原始类
            declaredFields = clazz.getSuperclass().getDeclaredField(fieldName);
            declaredFields.setAccessible(true);
            if (declaredFields.getType().isAssignableFrom(Integer.class)) {
                setValue = Integer.valueOf(value.toString());
            }
            declaredFields.set(bean, setValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new BeansException("Fail set Bean field value");
        }
    }

}
