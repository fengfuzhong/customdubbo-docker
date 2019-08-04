package com.feng.rpc.protocol.dubbo;

import com.feng.rpc.common.Invocation;
import com.feng.rpc.register.RegistMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class NettyChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        System.out.println("收到了一位小姐姐的搭讪，有点小紧张我要怎么办，求教！");
        String interfaceName = invocation.getInterfaceName();
        Class interfaceImplClass = (Class)RegistMap.getRadomURLOrClass(interfaceName,"class");
        Method method = interfaceImplClass.getMethod(invocation.getMethodName(),invocation.getParamtypes());
        String result = (String) method.invoke(interfaceImplClass.newInstance(),invocation.getObjects());
        ctx.write(result);
        ctx.flush();
    }
}