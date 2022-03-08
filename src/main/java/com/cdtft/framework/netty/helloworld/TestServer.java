package com.cdtft.framework.netty.helloworld;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wang.cheng
 * @date 2019/9/17 0:12
 * @email 453451180@qq.com
 **/
public class TestServer {

    public static void main(String[] args) {

        //创建两个线程组
        //用于接受客户端的请求链接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //接受主线程的任务
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //自定义服务器端的初始化器
                    .childHandler(new TestServerInitializer());

            ChannelFuture channelFuture = bootstrap.bind(9090).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
