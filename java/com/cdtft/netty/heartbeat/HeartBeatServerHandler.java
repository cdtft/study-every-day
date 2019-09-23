package com.cdtft.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author wang.cheng
 * @date 2019/9/23 21:54
 * @email 453451180@qq.com
 **/
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case ALL_IDLE:
                    printEvent(ctx, IdleState.ALL_IDLE.toString());
                    break;
                case READER_IDLE:
                    printEvent(ctx, IdleState.READER_IDLE.toString());
                    break;
                case WRITER_IDLE:
                    printEvent(ctx, IdleState.WRITER_IDLE.toString());
                    break;
                 default:
                     break;
            }
        }
    }

    private static void printEvent(ChannelHandlerContext ctx, String event) {
        System.out.println(ctx.channel().remoteAddress() + event);
    }
}
