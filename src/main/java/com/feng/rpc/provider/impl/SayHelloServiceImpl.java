package com.feng.rpc.provider.impl;

import com.feng.rpc.common.api.SayHelloService;

public class SayHelloServiceImpl implements SayHelloService {
    public String sayHello(String name) {
        System.out.println("面对小姐姐的搭讪，此刻的我小鹿乱撞，哎呀，好羞涩！");
        return "just,T hear you said:" + name+ "T am AlexFeng,nice to meet you too!";
    }
}
