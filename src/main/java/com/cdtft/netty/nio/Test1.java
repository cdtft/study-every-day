package com.cdtft.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author wang.cheng
 * @date 2019/10/16 22:57
 * @email 453451180@qq.com
 **/
public class Test1 {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            int randomInt = new SecureRandom().nextInt();
            intBuffer.put(randomInt);
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
