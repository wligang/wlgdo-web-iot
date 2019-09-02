package com.wlgdo.webiot.netty;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : Ligang.Wang[wangligang@163.com]
 * @date : 2019/3/14
 */
public class NettyGlobalProp {

    //默认端口
    @Value("netty.server.prot")
    public static final int SERVER_PORT = 0;
    //ip 地址
    @Value("netty.server.ip")
    public static final String SERVER_ADDRESS = null;

    private static volatile NettyGlobalProp instance = null;

    public static NettyGlobalProp getInstance() {
        if (instance == null) {
            synchronized (NettyGlobalProp.class) {
                if (instance == null) {
                    instance = new NettyGlobalProp();
                }
            }
        }
        return instance;
    }


    public BiMap<Integer, Channel> biMap = HashBiMap.create();

    public static void setInstance(NettyGlobalProp instance) {
        NettyGlobalProp.instance = instance;
    }

    public BiMap<Integer, Channel> getBiMap() {
        return biMap;
    }

    public void setBiMap(BiMap<Integer, Channel> biMap) {
        this.biMap = biMap;
    }
}
