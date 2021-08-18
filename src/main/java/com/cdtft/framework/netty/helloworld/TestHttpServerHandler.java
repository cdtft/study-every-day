package com.cdtft.framework.netty.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义http处理器
 *
 * @author : wangcheng
 * @date : 2019年09月17日 10:39
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass());

        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            if(httpRequest.uri().equals("favicon.ico")) {
                return;
            }
            //返回类容.
            ByteBuf content = Unpooled.copiedBuffer("this is my hello world", CharsetUtil.UTF_8);

            //返回体
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //返回客户端
            ctx.writeAndFlush(response);

            //请求完成关闭通道连接，可以判断使用的http协议版本
            ctx.channel().close();
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh hh:mm:ss");
        System.out.println("channelRegistered " + format.format(new Date()));
        Thread.sleep(1000);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh hh:mm:ss");
        System.out.println("channelInactive " + format.format(new Date()));
        Thread.sleep(1000);
        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh hh:mm:ss");
        System.out.println("channelActive " + format.format(new Date()));
        Thread.sleep(1000);
        super.channelActive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh hh:mm:ss");
        System.out.println("channelUnregistered " + format.format(new Date()));
        Thread.sleep(1000);
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh hh:mm:ss");
        System.out.println("handlerAdded " + format.format(new Date()));
        Thread.sleep(1000);
        super.handlerAdded(ctx);
    }

    /**
     * 基于http 1.0短连接协议，在请求结束后连接就会关闭
     * 基于http 1.1长连接歇息，在请求结束三秒后才会关闭连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-hh hh:mm:ss");
        System.out.println("handlerRemoved " + format.format(new Date()));
        Thread.sleep(1000);
        super.handlerRemoved(ctx);
    }
}
