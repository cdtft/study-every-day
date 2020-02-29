package com.cdtft.netty.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wang.cheng
 * @date 2019/10/16 23:13
 * @email 453451180@qq.com
 **/
public class NioWriteFile {

    public static void main(String[] args) throws IOException {

        //节点流
        FileOutputStream fileOutputStream = new FileOutputStream("test2.md");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] messageBytes = "this is nio".getBytes();
        for (int i = 0; i < messageBytes.length; i++) {
            byteBuffer.put(messageBytes[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);
    }
}
