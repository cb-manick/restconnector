package org.cb.restconnector.config;

public class EndpointConfig {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public int getConnectTimeoutInSec() {
        return connectTimeoutInSec;
    }

    public void setConnectTimeoutInSec(int connectTimeoutInSec) {
        this.connectTimeoutInSec = connectTimeoutInSec;
    }

    public int getRequestTimeoutInSec() {
        return requestTimeoutInSec;
    }

    public void setRequestTimeoutInSec(int requestTimeoutInSec) {
        this.requestTimeoutInSec = requestTimeoutInSec;
    }

    private int connectTimeoutInSec;
    private int requestTimeoutInSec;

}
