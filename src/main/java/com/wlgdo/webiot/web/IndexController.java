package com.wlgdo.webiot.web;

import com.wlgdo.webiot.netty.NettyGlobalProp;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/14
 */

@RestController
public class IndexController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IndexService indexService;


    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Object index(HttpServletRequest request) {
        logger.info(" The index page have been invist");
        logger.info("{}:{}", request.getRemoteHost(), request.getRemotePort());

        String welcomeHtml = "<html><h1>Wellcome to wlgdo web netty !!!<br>###</h1></html>";

        int terminalNum = NettyGlobalProp.getInstance().getBiMap().size();

        return welcomeHtml.replace("###", String.format("目前链接<b>[%s]</b>个终端", terminalNum));
    }

    @RequestMapping(value = "send/{id}/{msg}", method = RequestMethod.GET)
    public Object send(@PathVariable String msg, @PathVariable Integer id) {
        logger.info("begin send message to client:{}", id);
        if (NettyGlobalProp.getInstance().getBiMap().containsKey(id)) {
            Channel channle = NettyGlobalProp.getInstance().getBiMap().get(id);
            channle.writeAndFlush(msg);
            return "successful";
        }
        return "failure";

    }
}
