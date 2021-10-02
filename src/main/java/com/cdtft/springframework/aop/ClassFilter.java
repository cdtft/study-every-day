package com.cdtft.springframework.aop;

/**
 * @author: wangcheng
 * @date: 2021年10月01 19:06
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
