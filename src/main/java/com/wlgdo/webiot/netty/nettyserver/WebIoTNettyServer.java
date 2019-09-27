package com.wlgdo.webiot.netty.nettyserver;

import com.wlgdo.webiot.netty.NettyGlobalProp;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Value;

/***
 * @Description: netty service
 * @author Ligang.Wang[Feify@wlgdo.com]
 * @date 2019年3月14日
 */
public class WebIoTNettyServer {


    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final int BIZTHREADSIZE = 100;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void init() throws Exception {


        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                // TODO Auto-generated method stub
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));

                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new WebIoTServerHandler());
            }

        });


        //此处启动netty服务
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("【TCP服务器IP】" + NettyGlobalProp.SERVER_ADDRESS + "【TCP服务器PORT】" + NettyGlobalProp.SERVER_PORT);
                ChannelFuture f = null;
                try {
                    f = bootstrap.bind(NettyGlobalProp.SERVER_ADDRESS, NettyGlobalProp.SERVER_PORT).sync();
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }


    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

}
