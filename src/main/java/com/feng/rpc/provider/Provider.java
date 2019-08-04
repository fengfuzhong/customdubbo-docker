package com.feng.rpc.provider;

import com.feng.rpc.common.Protocol;
import com.feng.rpc.common.ProtocolFactory;
import com.feng.rpc.common.URL;
import com.feng.rpc.common.api.SayHelloService;
import com.feng.rpc.provider.impl.SayHelloServiceImpl;
import com.feng.rpc.register.RegistMap;

import java.net.InetAddress;

public class Provider {

    public static void main(String args[]) {
        InetAddress address;
        String myIp="localhost";
        try {
            address = InetAddress.getLocalHost();
            myIp= address.getHostAddress();
        }catch(Exception e){
            e.printStackTrace();

        }
        //服务注册
        URL url = new URL(myIp, 8080);
        RegistMap.register(SayHelloService.class.getName(), url, SayHelloServiceImpl.class);

        //服务启动
        //Protocol protocl = ProtocolFactory.getProtocol("http");
        Protocol protocl = ProtocolFactory.getProtocol("netty");
        protocl.start(url);
    }
}
