package com.cdtft.springframework.core.io;

/**
 *
 *
 * @author: wangcheng
 * @date: 2021年08月27 17:26
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            //do nothing
        }
        if (cl == null) {
            cl = ClassUtils.getDefaultClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }

}
