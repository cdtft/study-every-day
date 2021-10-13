package com.cdtft.springframework.aop;

import com.cdtft.springframework.aop.aspectj.AspectjExpressionPointcut;
import org.aopalliance.aop.Advice;

/**
 * @author: wangcheng
 * @date: 2021年10月12 14:10
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectjExpressionPointcut pointcut;

    private Advisor advisor;

    private String expression;

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectjExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advisor.getAdvice();
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
