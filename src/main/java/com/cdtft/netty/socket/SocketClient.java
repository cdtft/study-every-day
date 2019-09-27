package com.cdtft.netty.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * socket客户端
 *
 * @author : wangcheng
 * @date : 2019年09月18日 13:46
 */
public class SocketClient {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("websocket client开始启动");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {


            Bootstrap bootstrap = new Bootstrap().group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new SocketClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9091).sync();
            channelFuture.channel().closeFuture().sync();
        }  finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
