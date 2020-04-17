package com.cdtft.classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author : wangcheng
 * @date : 2020年04月16日 14:35
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = Class.forName("com.cdtft.classloader.AndroidPhone");
        Constructor constructor = clazz.getDeclaredConstructor();
        AndroidPhone androidPhone = (AndroidPhone) constructor.newInstance();
        androidPhone.call();

//        Phone phone1 = PhoneManager.getPhone(10);
//        phone1.call();
//
//        ClassLoader.getSystemClassLoader().loadClass("com.cdtft.classloader.ApplePhone");
//        //new ApplePhone();
//        Phone phone2 = PhoneManager.getPhone(5000);
//        phone2.call();
    }
}
