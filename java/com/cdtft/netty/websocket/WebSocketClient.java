package com.cdtft.netty.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * websocket客户端
 *
 * @author : wangcheng
 * @date : 2019年09月18日 13:46
 */
public class WebSocketClient {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("websocket client开始启动");
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {


            Bootstrap bootstrap = new Bootstrap().group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new WebSocketClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9091).sync();
            channelFuture.channel().closeFuture().sync();
        }  finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
