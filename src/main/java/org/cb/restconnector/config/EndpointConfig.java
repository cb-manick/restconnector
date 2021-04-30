package org.cb.restconnector.config;

import org.cb.restconnector.Scheme;

public class EndpointConfig {
  private String host;
  private int connectTimeoutInSec;
  private int requestTimeoutInSec;

  public EndpointConfig(String host, int connectTimeoutInSec, int requestTimeoutInSec, Scheme scheme) {
    this.host = host;
    this.connectTimeoutInSec = connectTimeoutInSec;
    this.requestTimeoutInSec = requestTimeoutInSec;
    this.scheme = scheme;
  }

  public Scheme getScheme() {
    return scheme;
  }

  public void setScheme(Scheme scheme) {
    this.scheme = scheme;
  }

  private Scheme scheme;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

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
}
