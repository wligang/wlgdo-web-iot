package com.wlgdo.webiot.web;

import com.wlgdo.webiot.netty.NettyGlobalProp;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Ligang.Wang[Feify@wlgdo.cn]
 * @date : 2019/3/14
 */
@Slf4j
@RestController
@AllArgsConstructor
public class IndexController {

    /**
     * 统计有多少个连接
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Object index(HttpServletRequest request) {
        log.info("The index page have been invist{}:{}", request.getRemoteHost(), request.getRemotePort());
        String welcomeHtml = "<html><h1>Wellcome to wlgdo web netty !!!<br>###</h1></html>";
        int terminalNum = NettyGlobalProp.getInstance().getBiMap().size();
        return welcomeHtml.replace("###", String.format("目前链接<b>[%s]</b>个终端", terminalNum));
    }

    /**
     * 向终端发送消息
     *
     * @param msg
     * @param id
     * @return
     */
    @RequestMapping(value = "send/{id}/{msg}", method = RequestMethod.GET)
    public Object send(@PathVariable String msg, @PathVariable Integer id) {
        log.info("begin send message to client:{},{}", id, msg);
        if (NettyGlobalProp.getInstance().getBiMap().containsKey(id)) {
            Channel channle = NettyGlobalProp.getInstance().getBiMap().get(id);
            channle.writeAndFlush(msg);
            channle.writeAndFlush(new TextWebSocketFrame(String.format("服务器收到消息[%s]:%s",
                    new SimpleDateFormat("HH:mm:ss").format(new Date()), msg)));
            return "successful";
        }
        return "failure";
    }
}
