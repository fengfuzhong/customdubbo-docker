package com.feng.rpc.common;

import com.feng.rpc.protocol.dubbo.NettyProtocl;
import com.feng.rpc.protocol.http.HttpProtocol;

public class ProtocolFactory {
    public static Protocol getProtocol(String protocolType) {
        Protocol protocol = null;
        switch (protocolType) {
            case "http":
                protocol = new HttpProtocol();
                break;
            default:
                protocol = new NettyProtocl();
                break;
        }
        return protocol;
    }
}
