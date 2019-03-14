package com.wlgdo.webiot;

import com.wlgdo.webiot.netty.WebIoTNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author Ligang.Wang[wlgchun@163.com]
 */
@SpringBootApplication
public class WebiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebiotApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("wlgdo web netty service is initting...");

        WebIoTNettyServer.init();

        System.out.println("wlgdo web netty server init successful !!!");
    }

}
