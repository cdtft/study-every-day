package com.cdtft.sdk.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : wangcheng
 * @date : 2020年02月16日 13:59
 */
public class IOTest {

    @Test
    public void BufferReaderTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String outStr = null;
        do {
            outStr = bufferedReader.readLine();
            if (outStr != null ) {
                System.out.println(outStr);
            }
        } while (!outStr.equals("end"));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String outStr = null;
        do {
            outStr = bufferedReader.readLine();
            if (outStr != null ) {
                System.out.println(outStr);
            }
        } while (!outStr.equals("end"));
    }

}
