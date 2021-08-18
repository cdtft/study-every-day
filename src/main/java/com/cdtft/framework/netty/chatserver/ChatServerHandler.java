package com.cdtft.framework.netty.chatserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 广播处理
 *
 * @author wang.cheng
 * @date 2019/9/21 23:08
 * @email 453451180@qq.com
 **/
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channels.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("收到来自" + channel.remoteAddress() + "的消息：" + msg + "\n");
            } else {
                ch.writeAndFlush("自己发出消息" + channel.remoteAddress() + "的消息：" + msg + "\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush("服务器-" + channel.remoteAddress() + "加入\n");
        channels.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器-" + ctx.channel().remoteAddress() + "加入\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器-" + ctx.channel().remoteAddress() + "下线\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //连接断开netty会自动remove channel
        System.out.println("服务器-" + channel.remoteAddress() + "断开\n");
        channels.writeAndFlush("服务器-" + channel.remoteAddress() + "断开\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
