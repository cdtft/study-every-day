package com.cdtft.springframework.core.io;

/**
 * @author: wangcheng
 * @date: 2021年08月31 09:35
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
