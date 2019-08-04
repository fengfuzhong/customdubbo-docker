package com.feng.rpc.protocol.http;

import com.feng.rpc.common.Invocation;
import com.feng.rpc.common.Protocol;
import com.feng.rpc.common.URL;

public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getUrl(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.post(url.getUrl(), url.getPort(), invocation);
    }
}
