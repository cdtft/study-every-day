package com.cdtft.netty.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * websocket服务器端的自定义处理器
 *
 * @author wang.cheng
 * @date 2019/9/17 23:44
 * @email 453451180@qq.com
 **/
public class SocketServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("this is start");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印来自客户端的信息
        System.out.println(ctx.channel().remoteAddress() + ":" + msg);
        //向客户端写信息
        ctx.channel().writeAndFlush("i an websocket server:" + UUID.randomUUID().toString().substring(1));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
