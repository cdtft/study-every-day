package com.cdtft.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: wangcheng
 * @date: 2021年08月31 09:50
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location);
        }
        try {
            URL url = new URL(location);
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            return new FileSystemResource(location);
        }
    }
}
