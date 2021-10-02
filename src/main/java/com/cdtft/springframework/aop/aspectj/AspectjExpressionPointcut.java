package com.cdtft.springframework.aop.aspectj;

import com.cdtft.springframework.aop.ClassFilter;
import com.cdtft.springframework.aop.MethodMatcher;
import com.cdtft.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: wangcheng
 * @date: 2021年10月01 19:15
 */
public class AspectjExpressionPointcut implements Pointcut, MethodMatcher, ClassFilter {

    public static final Set<PointcutPrimitive> SUPPORT_PRIMITIVE = new HashSet<>();

    static {
        SUPPORT_PRIMITIVE.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectjExpressionPointcut(String expresion) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORT_PRIMITIVE, this.getClass().getClassLoader());
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expresion);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    @Override
    public boolean matches(Class<?> targetClass) {
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

}
