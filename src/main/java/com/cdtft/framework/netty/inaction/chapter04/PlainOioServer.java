package com.cdtft.framework.netty.inaction.chapter04;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 使用阻塞IO
 */
public class PlainOioServer {

    private static final String HI = "hi!\r\n";

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket();
        try {
            for (; ; ) {
                //接受连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                new Thread(() -> {
                    OutputStream outputStream;
                    try {
                        outputStream = clientSocket.getOutputStream();
                        //将消息写给已连接的客户端
                        outputStream.write(HI.getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
