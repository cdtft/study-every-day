package com.cdtft.netty.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author wang.cheng
 * @date 2019/9/23 21:47
 * @email 453451180@qq.com
 **/
public class HeartBeatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new IdleStateHandler(3, 3, 3, TimeUnit.SECONDS));
        channelPipeline.addLast(new HeartBeatServerHandler());
    }
}
