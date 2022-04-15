package com.cdtft.framework.netty.inaction.chapter12;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: wangcheng
 * @date: 2022年04月15 10:24
 */
public class ChatServerInitializer extends ChannelInitializer<Channel> {

    private final ChannelGroup channelGroup;

    public ChatServerInitializer(ChannelGroup channelGroup) {
        this.channelGroup = channelGroup;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //将字节码解码为HttpRequest、HttpContent和LastHttpContent
        //并将HttpRequest、HttpContent、LastHttpContent编码为字节
        pipeline.addLast(new HttpServerCodec());
        //写入文件内容
        pipeline.addLast(new ChunkedWriteHandler());
        //将httpMessage和它跟随的多个HttpContent聚合为单个的FullHttpRequest或则FullHttpResponse
        //安装后ChannelPipeline中的下一个ChannelHandler只会收到完整的HTTP请求或响应
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        //处理FullHttpRequest
        pipeline.addLast(new HttpRequestHandler("/ws"));
        //按照WebSocket规范要求，处理WebSocket升级握手、PingWenSocketFrame、PongWenSocketFrame
        //和CloseWebSocketFrame
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler(channelGroup));
    }

}
