package com.cdtft.springframework.aop.framework;

import com.cdtft.springframework.aop.AdvisedSupport;
import com.cdtft.springframework.aop.aspectj.AopProxy;

/**
 * @author: wangcheng
 * @date: 2021年10月12 14:30
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        //基于接口创建代理
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
