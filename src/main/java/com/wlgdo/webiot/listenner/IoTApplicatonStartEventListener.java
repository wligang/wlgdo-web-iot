package com.wlgdo.webiot.listenner;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author : Ligang.Wang[Feify@wlgdo.cn]
 * @date : 2019/3/21
 */
public class IoTApplicatonStartEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("Spring start successful");
    }


}
