package com.cdtft.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 添加http的处理器
 *
 * @author : wangcheng
 * @date : 2019年09月17日 10:32
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("httpServerCodec", new HttpServerCodec());
        //添加自定义http处理器
        channelPipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
