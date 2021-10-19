package com.cdtft.springframework.beans.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author: wangcheng
 * @date: 2021年10月18 11:13
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Value {

    String value();

}
