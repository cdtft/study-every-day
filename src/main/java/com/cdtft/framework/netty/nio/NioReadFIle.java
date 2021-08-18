package com.cdtft.framework.netty.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wang.cheng
 * @date 2019/10/16 23:23
 * @email 453451180@qq.com
 **/
public class NioReadFIle {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test2.md");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(521);
        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }
}
