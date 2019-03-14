package com.wlgdo.webiot.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/11
 */

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("server receive message :" + msg);
        ctx.channel().writeAndFlush("hello clients， server already accept your message" + msg);
//        ctx.close();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("【channelActive】。。。");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("【exception is general】");
    }
}
