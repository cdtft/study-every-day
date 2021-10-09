package com.cdtft.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author: wangcheng
 * @date: 2021年10月08 11:33
 */
public class AdvisedSupport {

    private boolean proxyTargetClass = false;

    /**
     * 被代理的对象
     */
    private TargetSource targetSource;

    /**
     * 方法连接器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 验证目标方法是否符合通知条件
     */
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
