package com.wlgdo.webiot.web;

import com.google.common.collect.BiMap;
import com.wlgdo.webiot.netty.NettyGlobalProp;
import org.springframework.stereotype.Service;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/14
 */

@Service
public class IndexService {


    public BiMap getCurrentOnlineDevice() {
        return NettyGlobalProp.getInstance().getBiMap();
    }
}
