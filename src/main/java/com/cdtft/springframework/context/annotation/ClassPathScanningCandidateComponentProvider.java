package com.cdtft.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wangcheng
 * @date: 2021年10月14 14:31
 */
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponent(String basePackage) {

        Set<BeanDefinition> candidates = new HashSet<>();

        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
