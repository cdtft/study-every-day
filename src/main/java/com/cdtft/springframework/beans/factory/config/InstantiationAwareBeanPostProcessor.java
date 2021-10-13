package com.cdtft.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.cdtft.springframework.beans.PropertyValues;

/**
 * 在bean创建的时候会特殊的去判断是否是该种类型
 *
 * @author: wangcheng
 * @date: 2021年10月12 16:03
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 自身方法，是最先执行的方法，它在目标对象实例化之前调用，该方法的返回值类型是Object，我们可以返回任何类型的值。
     * 由于这个时候目标对象还未实例化，所以这个返回值可以用来代替原本该生成的目标对象的实例(比如代理对象)。
     * 如果该方法的返回值代替原本该生成的目标对象，后续只有postProcessAfterInitialization方法会调用，
     * 其它方法不再调用；否则按照正常的流程走
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

    /**
     * 对属性值进行修改，如果postProcessAfterInstantiation方法返回false，该方法可能不会被调用。可以在该方法内对属性值进行修改
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeanException;

}
