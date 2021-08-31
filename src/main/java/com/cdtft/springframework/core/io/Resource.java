package com.cdtft.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: wangcheng
 * @date: 2021年08月27 16:39
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
