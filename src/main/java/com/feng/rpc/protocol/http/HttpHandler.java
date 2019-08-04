package com.feng.rpc.protocol.http;

import com.feng.rpc.common.Invocation;
import com.feng.rpc.register.RegistMap;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        // 获取对象
        try {
            InputStream is = req.getInputStream();
            ObjectInputStream objectInputStream;
            objectInputStream = new ObjectInputStream(is);
            Invocation invocation = (Invocation) objectInputStream.readObject();
            String interfaceName = invocation.getInterfaceName();
            Class interfaceImplClass = (Class)RegistMap.getRadomURLOrClass(interfaceName,"class");
            Method method = interfaceImplClass.getMethod(invocation.getMethodName(),invocation.getParamtypes());
            String result = (String) method.invoke(interfaceImplClass.newInstance(),invocation.getObjects());
            IOUtils.write(result,resp.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
