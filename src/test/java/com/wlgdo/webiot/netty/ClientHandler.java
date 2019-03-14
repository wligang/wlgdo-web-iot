package com.wlgdo.webiot.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/11
 */

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().localAddress();
        System.out.println(String.format("Client port:{%s}", socketAddress.getPort()));
        System.out.println("【client接收到服务器返回的消息】:" + msg);

        //ctx.channel().writeAndFlush("Ok");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("【client exception is general】");
    }
}
