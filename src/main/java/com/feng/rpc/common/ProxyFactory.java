package com.feng.rpc.common;

import com.feng.rpc.register.RegistMap;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory<T> {
    public static <T> T getProxy(final String protocolType, final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Protocol protocol = ProtocolFactory.getProtocol(protocolType);
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                URL url = (URL) RegistMap.getRadomURLOrClass(interfaceClass.getName(), "url");
                return protocol.send(url, invocation);
            }
        });
    }
}
