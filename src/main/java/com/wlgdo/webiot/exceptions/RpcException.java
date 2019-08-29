package com.wlgdo.webiot.exceptions;

/**
 * @author : Ligang.Wang[wlgchun@163.com]
 * @date : 2019/3/19
 */

public class RpcException extends Exception {

    String exceptionMsg; // 定义String类型变量

    public RpcException(String exceptionMsg) {
        exceptionMsg = exceptionMsg;
    }

    public String getMexceptionMsg() {
        return exceptionMsg;
    }
}
