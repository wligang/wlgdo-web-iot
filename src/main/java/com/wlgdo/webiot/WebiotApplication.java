package com.wlgdo.webiot;

import com.wlgdo.webiot.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WebiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebiotApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("wlgdo web netty is initting...");
        try {
            NettyServer.init();
        } catch (Exception e) {
            System.out.println("netty server init exception");
            e.printStackTrace();
        }
        System.out.println("wlgdo web netty server init successful !!!");
    }

}
