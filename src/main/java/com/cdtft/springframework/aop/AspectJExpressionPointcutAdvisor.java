package com.cdtft.springframework.aop;

import com.cdtft.springframework.aop.aspectj.AspectjExpressionPointcut;
import org.aopalliance.aop.Advice;

/**
 * 切入点和通知的集合
 *
 * @author: wangcheng
 * @date: 2021年10月12 14:10
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectjExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectjExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

}
