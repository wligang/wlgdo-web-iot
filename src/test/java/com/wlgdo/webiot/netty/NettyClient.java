package com.wlgdo.webiot.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date
 */
public class NettyClient implements Runnable {

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

                    pipeline.addLast("handler", new ClientHandler());
                }
            });

           //ChannelFuture f = b.connect("127.0.0.1", 8000).sync();
             ChannelFuture f = b.connect("119.3.237.172", 8000).sync();
            f.channel().writeAndFlush("Netty Hello Service!" + Thread.currentThread().getName() + ":--->:" + Thread.currentThread().getId());
            f.channel().closeFuture().sync();
            InetSocketAddress socketAddress = (InetSocketAddress) f.channel().localAddress();
            System.out.print(String.format("Client port:{%s}", socketAddress.getPort()));

        } catch (Exception e) {
            System.out.print("netty服务异常了:{}");
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
            System.out.print("netty服务端断开了链接:" + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) throws Exception {
        int time = 0;
        for (; ; ) {
            new Thread(new NettyClient(), "【this thread】" + time++ + " ").start();
            Thread.sleep(50);
            if (time>3000)
                break;
        }
    }


}
