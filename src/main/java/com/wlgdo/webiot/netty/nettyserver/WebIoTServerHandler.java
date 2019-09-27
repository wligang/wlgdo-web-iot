package com.wlgdo.webiot.netty.nettyserver;

import com.wlgdo.webiot.netty.NettyGlobalProp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * 消息处理器
 *
 * @author : Ligang.Wang[Feify@wlgdo.com]
 * @date : 2019/3/11
 */
public class WebIoTServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("server receive message :" + msg);
//        System.out.println("server receive message type:" + msg.getClass().getTypeName());
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("【客户端的信息】。。。" + address);
        ctx.channel().writeAndFlush("hello clients， server already accept your message" + msg);
        //   ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        NettyGlobalProp.getInstance().biMap.put(address.getPort(), ctx.channel());
        System.out.println("【channelActive】" + address.getPort());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("【exception is general】" + cause.getMessage());
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        NettyGlobalProp.getInstance().biMap.remove(address.getPort());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("【channelActive is remove】");
    }
}
