package com.feng.rpc.consumer;

import com.feng.rpc.common.ProxyFactory;
import com.feng.rpc.common.api.SayHelloService;

public class Comsumer {
    public static void main(String args[]) {
        SayHelloService helloService = ProxyFactory.getProxy("netty", SayHelloService.class);
        //SayHelloService helloService = ProxyFactory.getProxy("http", SayHelloService.class);
        String resullt = helloService.sayHello("My name is dubbo,nice to meet you!");
        System.out.println(resullt);
    }
}
