package com.cdtft.springframework.context.annotation;

import com.cdtft.springframework.beans.factory.config.BeanDefinition;
import com.cdtft.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.cdtft.springframework.stereotype.Component;
import com.cdtft.springframework.util.IStringUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * @author: wangcheng
 * @date: 2021年10月14 14:54
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponent = findCandidateComponent(basePackage);
            for (BeanDefinition beanDefinition : candidateComponent) {
                String scope = resolveBeanScope(beanDefinition);
                if (StringUtils.isNotBlank(scope)) {
                    beanDefinition.setScope(scope);
                }
                beanDefinitionRegistry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            String value = scope.value();
            if (StringUtils.isNotBlank(value)) {
                return value;
            }
        }
        return StringUtils.EMPTY;
    }

    /**
     * 没有定义bean的名称就使用
     *
     * @param beanDefinition
     * @return
     */
    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getBeanClass();
        Component component = clazz.getAnnotation(Component.class);

        String beanName = component.value();

        if (StringUtils.isBlank(beanName)) {
            beanName = IStringUtils.lowerFirst(beanDefinition.getBeanClass().getName());
        }
        return beanName;
    }

}
