package com.wlgdo.webiot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/14
 */

@RestController
public class IndexController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Object index() {


        return "<html><h1>Wellcome to wlgdo web netty !!!</h1></html>";
    }

}
