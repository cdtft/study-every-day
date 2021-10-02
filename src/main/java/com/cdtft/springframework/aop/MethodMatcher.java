package com.cdtft.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author: wangcheng
 * @date: 2021年10月01 19:07
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}
