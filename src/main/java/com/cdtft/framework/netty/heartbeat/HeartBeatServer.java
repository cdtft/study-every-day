package com.cdtft.framework.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 带心跳检测的服务端
 *
 * @author wang.cheng
 * @date 2019/9/23 19:50
 * @email 453451180@qq.com
 **/
public class HeartBeatServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
                    //日志处理Handler
                    .channel(NioServerSocketChannel.class)
                    //debug级别，没有日志让我感觉很奇怪
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HeartBeatServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind("localhost",9092).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
