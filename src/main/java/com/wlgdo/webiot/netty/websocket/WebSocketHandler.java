package com.wlgdo.webiot.netty.websocket;


import com.wlgdo.webiot.netty.NettyGlobalProp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("get msg：" + textWebSocketFrame.text());
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(String.format("服务器收到消息[%s]:%s",
                new SimpleDateFormat("HH:mm:ss").format(new Date()), textWebSocketFrame.text())));
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println(String.format("【channelActive:%s】", address.getPort()));
        NettyGlobalProp.getInstance().biMap.put(address.getPort(), ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println(String.format("【channl remove:%s】", address.getPort()));
        NettyGlobalProp.getInstance().biMap.remove(address.getPort());
    }
}
