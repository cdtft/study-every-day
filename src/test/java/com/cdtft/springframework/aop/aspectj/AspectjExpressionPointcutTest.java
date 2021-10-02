package com.cdtft.springframework.aop.aspectj;

import com.cdtft.springframework.beans.factory.support.UserDao;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author: wangcheng
 * @date: 2021年10月02 17:42
 */
public class AspectjExpressionPointcutTest {

    @Test
    public void matches() throws NoSuchMethodException {
        String expresion = "execution(* com.cdtft.springframework.beans.factory.support.UserDao.findByUserNameBy(..))";

        AspectjExpressionPointcut aspectjExpressionPointcut = new AspectjExpressionPointcut(expresion);

        Class<UserDao> clazz = UserDao.class;
        Method method = clazz.getDeclaredMethod("findByUserNameById", Integer.class);

        System.out.println("matches by method and class " + aspectjExpressionPointcut.matches(method, clazz));
        System.out.println("matches by class " + aspectjExpressionPointcut.matches(clazz));
    }

}