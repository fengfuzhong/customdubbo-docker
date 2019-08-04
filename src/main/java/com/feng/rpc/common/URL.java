package com.feng.rpc.common;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;
import java.util.Objects;

public class URL implements Serializable {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    private String url;
    private Integer port;

    public URL(String hostname, Integer port) {
        this.url = hostname;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URL url1 = (URL) o;
        return Objects.equals(url, url1.url) &&
                Objects.equals(port, url1.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, port);
    }
}
