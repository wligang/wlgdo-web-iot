package com.wlgdo.webiot;

import com.wlgdo.webiot.netty.nettyserver.WebIoTNettyServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebiotApplicationTests {

    @Test
    public void contextLoads() {


    }

    @Test
    public static void startNettyServer(String[] args) {
        System.out.println("开始启动TCP服务器...");
        try {
            WebIoTNettyServer.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
