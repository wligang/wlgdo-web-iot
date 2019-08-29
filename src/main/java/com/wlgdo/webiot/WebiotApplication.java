package com.wlgdo.webiot;

import com.wlgdo.webiot.listenner.IoTApplicatonCloseEventListener;
import com.wlgdo.webiot.listenner.IoTApplicatonStartEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;


/**
 * @author Ligang.Wang[wlgchun@163.com]
 */
@SpringBootApplication
public class WebiotApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WebiotApplication.class);

        application.addListeners(new IoTApplicatonStartEventListener());
        application.addListeners(new IoTApplicatonCloseEventListener());
        application.run(args);

    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("wlgdo web netty service is initting...");
        //WebIoTNettyServer.init();
        //WebSocketServer.init();
        System.out.println("wlgdo web netty server init successful !!!");
    }

}
