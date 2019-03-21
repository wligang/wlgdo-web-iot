package com.wlgdo.webiot.core;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author : Ligang.Wang[wangligang@karaku.cn]
 * @date : 2019/3/21
 */

public class IoTApplicatonEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("Spring start successful");
    }
}
