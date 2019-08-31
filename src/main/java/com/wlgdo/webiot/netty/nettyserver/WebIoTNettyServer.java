package com.wlgdo.webiot.netty.nettyserver;

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
 * @author Ligang.Wang[wlgchun@163.com]
 * @date 2019年3月14日
 */
public class WebIoTNettyServer {
    //ip 地址
    @Value("netty.server.ip")
    private static final String IP = null;
    //默认端口
    @Value("netty.server.prot")
    private static final int PORT = 0;

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
        System.out.println("【TCP服务器IP】" + IP + "【TCP服务器PORT】" + PORT);

        //此处启动netty服务
        Thread thread = new Thread() {
            @Override
            public void run() {
                ChannelFuture f = null;
                try {
                    f = bootstrap.bind(IP, PORT).sync();
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
