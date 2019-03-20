package com.wlgdo.webiot.netty.websocket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer {

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind("0.0.0.0", 8001).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println(String.format("websocket server had exception:[%s]", e));
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            System.out.println(String.format("websocket server start successful,threadId:[%s]", Thread.currentThread().getId()));
        }
    }

    public static void init() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                WebSocketServer webSocketServer = new WebSocketServer();
                webSocketServer.run();
            }
        };
        thread.start();
    }

    public static void main(String[] args) {

        WebSocketServer webSocketServer = new WebSocketServer();
        webSocketServer.run();

    }
}
