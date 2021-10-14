package com.cdtft.springframework.aop.aspectj;

import com.cdtft.springframework.aop.AdvisedSupport;
import com.cdtft.springframework.aop.TargetSource;
import com.cdtft.springframework.aop.framework.Cglib2AopProxy;
import com.cdtft.springframework.aop.framework.JdkDynamicAopProxy;
import com.cdtft.springframework.aop.interceptor.UserServiceInterceptor;
import com.cdtft.springframework.beans.factory.support.IUserService;
import com.cdtft.springframework.beans.factory.support.IUserServiceImpl;
import com.cdtft.springframework.beans.factory.support.UserDao;
import com.cdtft.springframework.context.support.ClassPathXmlApplicationContext;
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

    @Test
    public void testDynamic() {
        IUserService userService = new IUserServiceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectjExpressionPointcut("execution( * com.cdtft.springframework.beans.factory.support.IUserService.*(..))"));

        IUserService proxyJDKUserService = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        IUserService proxyCglibUserService = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();

        System.out.println("queryUserInfo: " + proxyCglibUserService.queryUserInfo());
        System.out.println("register: " + proxyJDKUserService.register("wangcheng"));

    }

    @Test
    public void testAOP() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();

        IUserService iUserService = context.getBean("iUserService", IUserService.class);

        System.out.println("测试结果:" + iUserService.queryUserInfo());

    }

}