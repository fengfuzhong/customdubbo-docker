package com.feng.rpc.protocol.dubbo;

import com.feng.rpc.common.Invocation;
import com.feng.rpc.common.Protocol;
import com.feng.rpc.common.URL;
import com.feng.rpc.protocol.dubbo.client.NettyClient;

public class NettyProtocl implements Protocol {
    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(url.getUrl(),url.getPort());

    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient=new NettyClient();
        return nettyClient.send(url.getUrl(),url.getPort(),invocation);
    }
}
