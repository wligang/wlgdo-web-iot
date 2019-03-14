package com.wlgdo.webiot.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/11
 */

public class WebIoTServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("server receive message :" + msg);

        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("【客户端的信息】。。。" + address);
        ctx.channel().writeAndFlush("hello clients， server already accept your message" + msg);
        //   ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("【channelActive】。。。");
        InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
        NettyGlobalProp.getInstance().biMap.put(address.getPort(), ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("【exception is general】");
    }
}
