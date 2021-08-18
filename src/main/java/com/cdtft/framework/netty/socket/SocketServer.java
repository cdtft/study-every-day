package com.cdtft.framework.netty.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * socket服务器端，netty固定模板
 *
 * @author wang.cheng
 * @date 2019/9/17 20:44
 * @email 453451180@qq.com
 **/
public class SocketServer {

    public static void main(String[] args) {
        System.out.println("websocket服务端开始启动");
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SocketServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(9091).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
